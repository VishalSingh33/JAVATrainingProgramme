package com.nvr.heartbeat.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.dto.RegistrationIdDto;
import com.nvr.heartbeat.entites.RegistrationId;
import com.nvr.heartbeat.entites.User;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.helper.ResponseCode;
import com.nvr.heartbeat.helper.ResponseHelper;
import com.nvr.heartbeat.repository.RegistrationIdRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class HeartBeatService {

	@Value("${device.id.regex}")
	private String idRegex;
	@Value("${status.active}")
	private String ACTIVE;
	@Value("${status.inactive}")
	private String INACTIVE;
	@Autowired
	private RocksDB rocksDB;
	@Autowired
	@Qualifier("redisTokenTemplate")
	private RedisTemplate<String, String> redisTokenTemplate;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JwtService jwtUtil;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private RegistrationIdRepository regIdRepository;
	private final Counter successCounter;
	private final Counter failureCounter;
	private final Timer requestTimer;
	private final Counter requestCounter;
	private final Counter redisOpsCounter;
	private final Timer redisOpsTimer;

	Response<?> response = null;
	Response<List<Map<String, Object>>> responses = null;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public HeartBeatService(MeterRegistry meterRegistry,
			// @Qualifier("redisTemplate") RedisTemplate<String, List<Map<String, Object>>> redisTemplate,
			@Qualifier("redisTokenTemplate") RedisTemplate<String, String> redisTokenTemplate) {

		this.requestCounter = meterRegistry.counter("device_live_status_requests_total");
		this.successCounter = meterRegistry.counter("device_live_status_success_total");
		this.failureCounter = meterRegistry.counter("device_live_status_failures_total");
		this.requestTimer = meterRegistry.timer("device_live_status_seconds");

		// Counter to track the number of Redis operations
		this.redisOpsCounter = meterRegistry.counter("redis_operations_total", "type", "all");
		// Timer to measure latency of Redis operations
		this.redisOpsTimer = meterRegistry.timer("redis_operation_latency_seconds", "type", "all");

		// this.redisTemplate = redisTemplate;
		this.redisTokenTemplate = redisTokenTemplate;
	}

	//// Device HeartBeat LIVE STATUS
	public Response<?> heartBeatLiveStatus(List<String> deviceIds, LoginUserDto loginUserDto) {

		User authenticatedUser = authenticationService.authenticate(loginUserDto);
		String token = redisTokenTemplate.opsForValue().get(loginUserDto.getEmail());
		jwtUtil.isTokenValid(token, authenticatedUser); // Validate token

		logger.info("NHB_NS_OO1 - reqParams:{}" + deviceIds);
		Map<String, Object> deviceStatuses = new ConcurrentHashMap<>();
		// Executor for async tasks
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<CompletableFuture<Void>> futures = new ArrayList<>();

		requestCounter.increment();

		// Fetch all Device statuses from rocksDB in parallel
		for (String deviceId : deviceIds) {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				try {
					logger.info("NHB_NS_OO1 - Processing deviceId: {}", deviceId);
					String patternDeviceId = deviceId + "#";

					boolean isActive = checkDeviceInRocksDB(patternDeviceId);

					if (isActive) {
						logger.info("NHB_NS_OO1 - Cache hit for deviceId: {}", deviceId);
						deviceStatuses.put(deviceId, ACTIVE);
					} else {
						logger.warn("NHB_NS_OO1 - deviceId not found in RocksDB: {}", deviceId);
						deviceStatuses.put(deviceId, "Device Not Found");
					}
				} catch (Exception ex) {
					logger.error("NHB_NS_OO1 - Error while processing deviceId: {}", deviceId, ex);
					deviceStatuses.put(deviceId, "No Response");
				}
			}, executor);
			futures.add(future);
		}
		// Wait for all futures to complete
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		logger.info("NHB_NS_OO1 - Shutting down executor...");
		executor.shutdown();
		Timer.Sample sample = Timer.start(); // prometheus
		long start = System.nanoTime();
		try {
			successCounter.increment(); // prometheus
			redisOpsCounter.increment(); // prometheus redis
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
				logger.warn("NHB_NS_OO1 - Executor shutdown forced due to timeout.");
			}
		} catch (InterruptedException e) {
			logger.error("NHB_NS_OO1 - Interrupted while waiting for executor shutdown.", e);
			failureCounter.increment(); // prometheus
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		} finally {
			sample.stop(requestTimer); // prometheus
			redisOpsTimer.record(System.nanoTime() - start, TimeUnit.NANOSECONDS); // Record latency prometheus redis
			sample.stop(redisOpsTimer);
		}
		List<Map<String, Object>> responseList = new ArrayList<>();
		deviceStatuses.forEach((deviceId, status) -> {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			responseMap.put("deviceId", deviceId);
			responseMap.put("status", status);

			if (ACTIVE.equalsIgnoreCase((String) status)) {
				responseMap.put("statusCode", 200);
				responseMap.put("message", ACTIVE);
			} else {
				responseMap.put("statusCode", 404);
				responseMap.put("message", "Device not found");
			}
			responseList.add(responseMap);
		});
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO1 - device statuses: {}", responseList);
		return responses;
	}


	//// Device Registration Heartbeat
	@SuppressWarnings("unused")
	public Response<?> registerDevice(RegistrationIdDto registrationIdDto, BindingResult bindingResult) {

		logger.info("NHB_NS_OO2 - registerDevice deviceId: {}, clientServiceName: {}, apiKey: {}",
				registrationIdDto.getDeviceId(),
				registrationIdDto.getClientServiceName(),
				registrationIdDto.getApiKey());
		// Validate Device ID
		if (!Pattern.matches(idRegex, registrationIdDto.getDeviceId())) {
			logger.error("NHB_NS_OO2 - Invalid Device ID Format(Need 5 groups of two hexadecimal characters followed by a colon): {}",
					registrationIdDto.getDeviceId());
			return ResponseHelper.getErrorResponse(ResponseCode.Error_occured,
					"NHB_NS_OO2 - Invalid Device ID format. Expected format(Need 5 groups of two hexadecimal characters followed by a colon): XX:XX:XX:XX:XX:XX");
		}
		Map<String, Object> mapDevice = new LinkedHashMap<>();
		List<Map<String, Object>> responseList = new ArrayList<>();
		// Use a map for efficient camera lookup during merging
		Timer.Sample sample = Timer.start(); // prometheus
		try {
			if (bindingResult.hasErrors()) {
				logger.error("NHB_NS_OO2 - Error - binding result has some errors");
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, "Some parameters invalid");
			}
			String apiKey = registrationIdDto.getApiKey();
			String clientServiceName = registrationIdDto.getClientServiceName();

			RegistrationId regId = regIdRepository.heartbeatVerification(apiKey, clientServiceName);
			logger.info("NHB_NS_OO2 - regId: {}", regId);

			if (regId.getApiKey() == null) {
				throw new RuntimeException(
						"NHB_NS_OO2 - API_KEY not found with Id: {}" + registrationIdDto.getApiKey());
			}
			// Build the final Device data
			mapDevice.put("apiKey", regId.getApiKey());
			// mapDevice.put("deviceId", regId.getDeviceId());

			responseList.add(mapDevice);
			// Store the updated device data back into rocksDB
			// redisTemplate.opsForValue().set(registrationIdDto.getDeviceId() + "#" + registrationIdDto.getApiKey(),
			// 		responseList, Duration.ofMinutes(regId.getTtl()));

			String rocksDbKey = registrationIdDto.getDeviceId() + "#" + registrationIdDto.getApiKey();
			byte[] rocksDbValue = new ObjectMapper().writeValueAsBytes(responseList);
			// Get the TTL in milliseconds (Duration converted to ms)
			long ttlMillis = Duration.ofMinutes(regId.getTtl()).toMillis();
			// Get the current time
			long currentTimeMillis = System.currentTimeMillis();
			// Calculate the expiration timestamp
			long expirationTime = currentTimeMillis + ttlMillis;
			// Combine value and expiration time
			String valueWithTTL = new String(rocksDbValue, StandardCharsets.UTF_8) + "|" + expirationTime;
			// Store the value with TTL (expiration time)
			rocksDB.put(rocksDbKey.getBytes(StandardCharsets.UTF_8), valueWithTTL.getBytes(StandardCharsets.UTF_8));
			logger.info("NHB_NS_OO2 - Data successfully stored in RocksDB for key: {}", rocksDbKey);

			sendStatusNotification(registrationIdDto.getDeviceId(), apiKey, ACTIVE);
			// Log and check if we need to send a notification
			logger.info("NHB_NS_OO2 - responseList: {} ", responseList);
			successCounter.increment(); // prometheus
		} catch (Exception e) {
			e.printStackTrace();
			failureCounter.increment(); // prometheus
			// Metrics.counter("device.id.status.count.excount").increment();
			logger.error("NHB_NS_OO2 - error - " + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND,
					"An error occurred while converting string to JSON");
		} finally {
			sample.stop(requestTimer); // prometheus
			logger.info("NHB_NS_OO2 - End");
		}
		// Return the merged response
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO2 - Device statuses: {}", responseList);
		return ResponseHelper.getSuccessResponse(null); // return responses;
	}

	public CompletableFuture<Void> sendStatusNotification(String deviceId, String apiKey, String status) {
		return CompletableFuture.runAsync(() -> {
			try {
				List<Map<String, Object>> deviceStatusList = new ArrayList<>();
				RegistrationId regId = regIdRepository.findByApiKey(apiKey.toString())
						.orElseThrow(() -> new RuntimeException("NHB_NS_OO3 - API KEY not found with Id: {} " + apiKey.toString()));

				// Check if the input is a single key or a queue of keys
				if (deviceId instanceof String) {
					// Handle single Device ID
					Map<String, Object> requestBody = new LinkedHashMap<>();
					requestBody.put("DeviceId", deviceId);
					requestBody.put("status", status);
					deviceStatusList.add(requestBody);
				} else {
					logger.warn("NHB_NS_OO3 - Unsupported type for keyId: {}", deviceId.getClass().getName());
					return;
				}
				// Log the status change notification
				logger.info("NHB_NS_OO3 - Sending Device status change notification for: {}", deviceStatusList);

				// Create headers and set content type
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// Prepare the HTTP request
				HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(deviceStatusList, headers);
				logger.info("NHB_NS_OO3 - Sending HTTP request: {}", request);

				// Send the POST request
				ResponseEntity<String> response = restTemplate.postForEntity(regId.getWebhookUrl(), request,
						String.class);

				// Log the response based on status
				if (response.getStatusCode().is2xxSuccessful()) {
					logger.info("NHB_NS_OO3 - Successfully sent Device status change notification: {}",
							deviceStatusList);
				} else {
					logger.warn("NHB_NS_OO3 - Failed to send Device status change notification. HTTP Status: {}",
							response.getStatusCode());
				}
			} catch (HttpClientErrorException | HttpServerErrorException httpException) {
				// Log client/server-side errors
				logger.error(
						"NHB_NS_OO3 - HTTP error occurred while sending Device status change notification. Error: {}, Response Body: {}",
						httpException.getStatusCode(), httpException.getResponseBodyAsString(), httpException);
			} catch (ResourceAccessException timeoutException) {
				// Handle timeout exceptions
				logger.error("NHB_NS_OO3 - Timeout occurred while sending Device status change notification: {}",
						timeoutException.getMessage(), timeoutException);
			} catch (Exception e) {
				// Catch all other exceptions
				logger.error(
						"NHB_NS_OO3 - Unexpected error occurred while sending Device status change notification: {}",
						e.getMessage(), e);
			} finally {
				logger.info("NHB_NS_OO3 - Cleared expired keys queue.");
			}
		});
	}

	@Scheduled(cron = "0 */2 * * * ?")
	public void checkInactiveDeviceIds() {
		// Create an iterator to scan all keys in RocksDB
		try (RocksIterator iterator = rocksDB.newIterator()) {
			iterator.seekToFirst(); // Start from the first key

			while (iterator.isValid()) {
				String rocksDbKey = new String(iterator.key(), StandardCharsets.UTF_8);
				byte[] valueBytes = iterator.value();

				// Check if the key has an associated value
				if (valueBytes != null) {
					String valueWithTTL = new String(valueBytes, StandardCharsets.UTF_8);
					String[] parts = valueWithTTL.split("\\|");

					// Extract the actual value and expiration timestamp
					String actualValue = parts[0];
					long expirationTime = Long.parseLong(parts[1]);
					logger.info("NHB_NS_OO4 - Parsed JSON Value: {}, Timestamp: {}", actualValue, expirationTime);

					// Check if the key has expired
					if (System.currentTimeMillis() > expirationTime) {
						// Key has expired, delete the key and send notification
						rocksDB.delete(rocksDbKey.getBytes(StandardCharsets.UTF_8));

						// Split the key to get deviceId and apiKey
						String[] keyParts = rocksDbKey.split("#");
						if (keyParts.length != 2) {
							logger.warn("NHB_NS_OO4 - Invalid key format: {}", rocksDbKey);
							iterator.next();
							continue;
						}
						String deviceId = keyParts[0];
						String apiKey = keyParts[1];
						logger.info("NHB_NS_OO4 - Parsed API Key: {}, Device ID: {}", apiKey, deviceId);
						// Trigger the notification asynchronously
						CompletableFuture.runAsync(() -> {
							sendStatusNotification(deviceId, apiKey, INACTIVE);
						});
					}
				}
				iterator.next(); // Move to the next key
			}
		} catch (Exception e) {
			logger.error("NHB_NS_OO4 - Error scanning RocksDB for expired keys", e);
		}
	}

	private boolean checkDeviceInRocksDB(String patternDeviceId) {
		try (RocksIterator iterator = rocksDB.newIterator()) {
			iterator.seekToFirst(); // Start from the first key

			while (iterator.isValid()) {
				String rocksDbKey = new String(iterator.key());
				logger.info("NHB_NS_OO5 - RocksDB Key format: {}", rocksDbKey);
				// Check if the key starts with the given pattern (deviceId#)
				if (rocksDbKey.startsWith(patternDeviceId)) {
					return true; // Device found
				}
				iterator.next(); // Move to the next key
			}
		} catch (Exception e) {
			logger.error("NHB_NS_OO5 - Error scanning RocksDB keys", e);
		}
		return false; // Device not found
	}

}