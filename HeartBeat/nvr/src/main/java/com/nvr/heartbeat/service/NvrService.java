package com.nvr.heartbeat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.entites.Camera;
import com.nvr.heartbeat.entites.NvrCamera;
import com.nvr.heartbeat.entites.User;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.helper.ResponseCode;
import com.nvr.heartbeat.helper.ResponseHelper;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class NvrService {

	@Autowired
	private RedisTemplate<String, List<Map<String, Object>>> redisTemplate;

	@Autowired
	@Qualifier("customRedisTemplate")
	private RedisTemplate<String, String> redisTokenTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JwtService jwtUtil;

	@Autowired
	private AuthenticationService authenticationService;

	private final Counter requestCounter;
	private final Counter successCounter;
	private final Counter failureCounter;
	private final Counter redisOpsCounter;
	private final Timer requestTimer;
	private final Timer redisOpsTimer;

	public NvrService(MeterRegistry meterRegistry, RedisTemplate<String, List<Map<String, Object>>> redisTemplate) {
		this.requestCounter = meterRegistry.counter("nvr_live_status_requests_total");
		this.successCounter = meterRegistry.counter("nvr_live_status_success_total");
		this.failureCounter = meterRegistry.counter("nvr_live_status_failures_total");
		this.requestTimer = meterRegistry.timer("nvr_live_status_seconds");
		this.redisTemplate = redisTemplate;
		// Counter to track the number of Redis operations
		this.redisOpsCounter = meterRegistry.counter("redis_operations_total", "type", "all");
		// Timer to measure latency of Redis operations
		this.redisOpsTimer = meterRegistry.timer("redis_operation_latency_seconds", "type", "all");
	}

	Response<?> response = null;

	Response<List<Map<String, Object>>> responses = null;

	private ObjectMapper mapper = new ObjectMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${nvr.id.regex}")
	private String nvrIdRegex;

	@Value("${status.active}")
	private String ACTIVE;

	@Value("${status.inactive}")
	private String INACTIVE;

	@Value("${inactivity.threshold.ms}")
	private long INACTIVITY_THRESHOLD_MS;

	@Value("${inactivity.scheduler.ms}")
	private long SCHEDULER_TTL;

	@Value("${nvr.status.change.url}")
	private String nvrStatusChangeUrl;

	@Value("${cam.status.change.url}")
	private String camStatusChangeUrl;

	//// NVR GET LIVE STATUS
	public Response<?> nvrLiveStatus(List<String> nvrIds, LoginUserDto loginUserDto) {
		
		User authenticatedUser = authenticationService.authenticate(loginUserDto);
		String token = redisTokenTemplate.opsForValue().get(loginUserDto.getEmail());
		jwtUtil.isTokenValid(token, authenticatedUser); // Validate token

		logger.info("NHB_NS_OO1 - reqParams:{}" + nvrIds);
		Map<String, Object> nvrStatuses = new ConcurrentHashMap<>();
		// Executor for async tasks
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<CompletableFuture<Void>> futures = new ArrayList<>();

		requestCounter.increment();

		// Fetch all NVR statuses from Redis in parallel
		for (String nvrId : nvrIds) {
			CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
				logger.info("NHB_NS_OO1 - Processing nvrId: {}", nvrId);
				// String cachedStatus = (String) redisTemplate.opsForValue().get(nvrId);
				List<Map<String, Object>> cachedStatus = redisTemplate.opsForValue().get(nvrId);
				logger.info("NHB_NS_OO1 - Cached status for nvrId {}: {}", nvrId, cachedStatus);

				if (cachedStatus != null) {
					logger.info("NHB_NS_OO1 - Cache hit for nvrId: {}, Status: {}", nvrId, cachedStatus);
					nvrStatuses.put(nvrId, cachedStatus);
				} else {
					nvrStatuses.put(nvrId, "NVR Not Found");
					logger.warn("NHB_NS_OO1 - nvrId not found in cache: {}", nvrId);
				}
				return null;
			}, executor);

			futures.add(future.orTimeout(60, TimeUnit.SECONDS).exceptionally(ex -> {
				// Handle timeout scenario
				logger.error("NHB_NS_OO1 - Timeout while fetching status for nvrId: {}", nvrId, ex);
				nvrStatuses.put(nvrId, "No Response");
				return null;
			}));
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
		String stat = null;
		for (Map.Entry<String, Object> entry : nvrStatuses.entrySet()) {
			Map<String, Object> map = new LinkedHashMap<>(); // Create a new map for each entry
			String nvrId = entry.getKey();
			List<Map<String, Object>> existingData = redisTemplate.opsForValue().get(nvrId);
			logger.info("NHB_NS_OO1 - Processing data for NVR ID: {}, Data: {}", nvrId);

			if (existingData != null) {
				for (Map<String, Object> dataMap : existingData) {
					// Check if the map contains the 'status' key
					if (dataMap.containsKey("status")) {
						// Retrieve and print the status value
						stat = (String) dataMap.get("status");

						if (ACTIVE.equalsIgnoreCase(stat)) {
							map.put("nvrId", nvrId);
							map.put("status", stat);
							map.put("statusCode", 200);
							map.put("message", ACTIVE);
							responseList.add(map);
						} else if (INACTIVE.equalsIgnoreCase(stat)) {
							map.put("nvrId", nvrId);
							map.put("status", stat);
							map.put("statusCode", 404);
							map.put("message", INACTIVE);
							responseList.add(map);
						}
					}
				}
			} else {
				map.put("nvrId", nvrId);
				map.put("status", "NVR not Found");
				map.put("statusCode", 404);
				map.put("message", "NVR not Found");
				responseList.add(map);
			}
		}
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO1 - NVR statuses: {}", responseList);
		return responses;
	}

	//// nvrId Registration Heartbeat with camerList
	@SuppressWarnings("unchecked")
	public Response<?> nvrCameraStatus(String nvrId, String request, BindingResult bindingResult) {

		logger.info("NHB_NS_OO2 - handleCameraStatusChange nvrId: {}", nvrId + request);
		// Validate NVR ID
		if (!Pattern.matches(nvrIdRegex, nvrId)) {
			logger.error("Invalid NVR ID Format(Need 5 groups of two hexadecimal characters followed by a colon): {}",
					nvrId);
			return ResponseHelper.getErrorResponse(ResponseCode.Error_occured,
					"Invalid NVR ID format. Expected format(Need 5 groups of two hexadecimal characters followed by a colon): XX:XX:XX:XX:XX:XX");
		}
		List<Map<String, Object>> cameraList = new ArrayList<>();
		Map<String, Object> mapNvr = new LinkedHashMap<>();
		List<Map<String, Object>> responseList = new ArrayList<>();
		// Use a map for efficient camera lookup during merging
		Map<String, Map<String, Object>> cameraMap = new LinkedHashMap<>();
		NvrCamera nvrRequest = null;
		String previousStatus = null;
		boolean isAlreadyNotified = false; // Flag to track notification status
		Timer.Sample sample = Timer.start(); // prometheus
		try {
			if (bindingResult.hasErrors()) {
				logger.error("NHB_NS_OO2 - Error - binding result has some errors");
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, "Some parameters invalid");
			}
			// Retrieve existing NVR data from Redis (if it exists)
			List<Map<String, Object>> existingData = redisTemplate.opsForValue().get(nvrId);

			// If there is existing data in Redis, merge the camera lists
			if (existingData != null) {
				for (Map<String, Object> existingCamera : existingData) {
					List<Map<String, Object>> existingCameras = (List<Map<String, Object>>) existingCamera
							.get("cameraList");
					if (existingCameras != null) {
						for (Map<String, Object> cam : existingCameras) {
							cameraMap.put((String) cam.get("cctvId"), cam); // Map by cctvId
						}
					}
					// Get the previous status of the NVR from Redis
					previousStatus = (String) existingCamera.get("status");
					isAlreadyNotified = Boolean.TRUE.equals(existingCamera.get("isAlreadyNotified"));
				}
			}
			// Process the new request if available
			if (request != null && !"".equals(request) && !request.isBlank()) {
				nvrRequest = mapper.readValue(request, NvrCamera.class);
				logger.info("NHB_NS_OO2 - JSON object: {}", nvrRequest.toString());
				// Access the list of cameras from the request
				List<Camera> cameras = nvrRequest.getCameraList();
				logger.info("NHB_NS_OO2 - List of cameras: {}", cameras);

				// Merge/Override with new camera data
				if (cameras != null) {
					for (Camera cam : cameras) {
						Map<String, Object> cameraData = new LinkedHashMap<>();
						cameraData.put("cctvId", cam.getCctvId());
						cameraData.put("status", cam.getStatus());
						// Merge: If the camera ID already exists, override its status
						cameraMap.put(cam.getCctvId(), cameraData);
						// Add a restTemplate for camStatus change
						sendCameraStatusChangeNotification(nvrId, cam.getCctvId(), cam.getStatus());
					}
				}
			}
			successCounter.increment(); // prometheus
			// Rebuild the merged camera list
			cameraList.addAll(cameraMap.values());

		} catch (JsonProcessingException e) {
			failureCounter.increment(); // prometheus
			// Metrics.counter("nvr.id.status.count.excount").increment();
			logger.error("NHB_NS_OO2 - error - " + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND,
					"An error occurred while converting string to JSON");
		} finally {
			sample.stop(requestTimer); // prometheus
			logger.info("NHB_NS_OO2 - End");
		}
		// Build the final NVR data
		mapNvr.put("nvrId", nvrId);
		mapNvr.put("status", ACTIVE);
		mapNvr.put("timestamp", System.currentTimeMillis());
		mapNvr.put("isAlreadyNotified", isAlreadyNotified); // Preserve the notification flag
		if (!cameraList.isEmpty()) {
			mapNvr.put("cameraList", cameraList);
		}
		responseList.add(mapNvr);
		// Store the updated NVR data back into Redis
		redisTemplate.opsForValue().set(nvrId, responseList);

		// Log and check if we need to send a notification
		logger.info("NHB_NS_OO2 - nvrId: {} previousStatus: {}, currentStatus: {}", nvrId, previousStatus, ACTIVE);

		// Send notification only if previous status was INACTIVE and current status is
		// ACTIVE
		if (!isAlreadyNotified && ACTIVE.equals(mapNvr.get("status"))) {
			sendNvrStatusChangeNotification(nvrId, ACTIVE);
			// logger.info("NHB_NS_OO2 - Sent notification for NVR status change from
			// INACTIVE to ACTIVE");
			logger.info("NHB_NS_OO2 - Sent notification for NVR status change to ACTIVE");
			mapNvr.put("isAlreadyNotified", true); // Mark the notification as sent
			redisTemplate.opsForValue().set(nvrId, responseList); // Update Redis with the new flag
		}
		// Return the merged response
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO2 - NVR statuses: {}", responseList);
		return ResponseHelper.getSuccessResponse(null); // return responses;
	}

	@Scheduled(cron = "0 */2 * * * ?")
	public void checkInactiveNvrIds() {
		// Check Redis connection
		@SuppressWarnings("null")
		Boolean isConnected = redisTemplate.getConnectionFactory().getConnection().ping() != null;
		if (!isConnected) {
			logger.error("NHB_NS_OO3 - Unable to connect to Redis.");
			return;
		}
		// Fetch all keys (NVR IDs) from Redis
		Set<String> nvrIds = new HashSet<>();
		try (Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(
				(RedisConnection connection) -> connection.scan(
						ScanOptions.scanOptions().match("*").count(1000).build()))) {
			while (cursor.hasNext()) {
				nvrIds.add(new String(cursor.next()));
			}
		} catch (Exception e) {
			logger.error("NHB_NS_OO3 - Error scanning keys from Redis: {}", e.getMessage(), e);
			return;
		}
		if (nvrIds.isEmpty()) {
			logger.warn("NHB_NS_OO3 - No keys found in Redis.");
			// Optional: Trigger notification if needed when no keys are found
			return;
		}
		logger.info("NHB_NS_OO3 - Fetched NVR IDs: {}", nvrIds);

		// Iterate over each NVR ID and process
		for (String nvrId : nvrIds) {
			List<Map<String, Object>> existingData = redisTemplate.opsForValue().get(nvrId);

			if (existingData == null || existingData.isEmpty()) {
				logger.info("NHB_NS_OO3 - No data found for NVR ID: {}", nvrId);
				continue;
			}
			boolean isInactive = false;
			Long lastHeartbeatTimestamp = null;

			for (Map<String, Object> nvrEntry : existingData) {
				lastHeartbeatTimestamp = (Long) nvrEntry.get("timestamp");
				String status = (String) nvrEntry.get("status");
				String nvrIdExtracted = (String) nvrEntry.get("nvrId");

				logger.info("NHB_NS_OO3 - nvrId: {}, Status: {}, lastHeartbeatTimestamp: {}",
						nvrIdExtracted, status, lastHeartbeatTimestamp);

				// Check if the NVR has been inactive for longer than the threshold
				if (lastHeartbeatTimestamp != null &&
						(System.currentTimeMillis() - lastHeartbeatTimestamp > INACTIVITY_THRESHOLD_MS)) {
					isInactive = true;
					logger.info("NHB_NS_OO3 - NVR ID: {} has been inactive for over {} ms",
							nvrId, INACTIVITY_THRESHOLD_MS);
				}
			}
			// Process inactive NVRs asynchronously
			if (isInactive) {
				CompletableFuture.runAsync(() -> processNvrIds(nvrId));
			}
		}
	}

	// Process the NVR IDs to check their status and mark inactive if needed
	private void processNvrIds(String nvrId) {
		if (nvrId == null || nvrId.isEmpty()) {
			logger.warn("NHB_NS_OO3 - Invalid NVR ID: {}", nvrId);
			return;
		}
		// Check TTL and process only if the key has expired
		// redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
		Long ttl = redisTemplate.getExpire(nvrId, TimeUnit.SECONDS);
		if (ttl == null || ttl > 0) {
			logger.info("NHB_NS_OO3 - NVR ID: {} is still active with TTL: {} seconds", nvrId, ttl);
			return;
		}
		// Retrieve existing data for the NVR ID
		List<Map<String, Object>> jsonStatus = redisTemplate.opsForValue().get(nvrId);

		if (jsonStatus != null && !jsonStatus.isEmpty()) {
			boolean statusUpdated = false;

			for (Map<String, Object> statusData : jsonStatus) {
				String status = (String) statusData.get("status");
				logger.info("NHB_NS_OO3 - Current status for NVR ID {}: {}", nvrId, status);

				// Update status to INACTIVE if it's null or ACTIVE
				if (status == null || ACTIVE.equals(status)) {
					statusData.put("status", INACTIVE);
					statusUpdated = true;
					logger.info("NHB_NS_OO3 - Marked NVR ID: {} as INACTIVE", nvrId);
					sendNvrStatusChangeNotification(nvrId, INACTIVE); // Notify external service
				}
			}
			// Update the Redis key if status was changed
			if (statusUpdated) {
				redisTemplate.opsForValue().set(nvrId, jsonStatus, Duration.ofMinutes(SCHEDULER_TTL));
				logger.info("NHB_NS_OO3 - Updated status and TTL for NVR ID: {}", nvrId);
			}
		}
		// Delete the NVR ID from Redis after marking it as inactive
		redisTemplate.delete(nvrId);
		logger.info("NHB_NS_OO3 - Deleted NVR ID: {} from Redis after marking it INACTIVE", nvrId);
		// });
	}

	private void sendNvrStatusChangeNotification(String nvrId, String status) {
		try {
			List<Map<String, Object>> nvrStatusList = new ArrayList<>();
			Map<String, Object> requestBody = new LinkedHashMap<>();
			requestBody.put("nvrId", nvrId);
			requestBody.put("status", status);
			nvrStatusList.add(requestBody);
			logger.info("NHB_NS_OO4 - Sending nvrStatus change notification for: {}",
					nvrStatusList);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// Send the notification as a JSON payload
			HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(nvrStatusList, headers);
			logger.info("NHB_NS_OO4 - Sending request as notification: {}", request);

			ResponseEntity<String> response = restTemplate.postForEntity(nvrStatusChangeUrl, request, String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				logger.info("NHB_NS_OO4 - NVR status change notification sent successfully for nvrId: {} with status: {}",
						nvrId, status);
			} else {
				logger.warn("NHB_NS_OO4 - Failed to send nvrStatus change notification for nvrId: {}", nvrId);
			}
		} catch (HttpClientErrorException | HttpServerErrorException httpException) {
			// Log specific details about client/server-side errors
			logger.error("NHB_NS_OO4 - HTTP error occurred while sending nvrStatus change notification for nvrId: {}. Error: {}",
					nvrId, httpException.getStatusCode(), httpException);

		} catch (ResourceAccessException timeoutException) {
			// Handle timeout exceptions
			logger.error("NHB_NS_OO4 - Timeout occurred while sending nvrStatus change notification for nvrId: {}",
					nvrId, timeoutException);

		} catch (Exception e) {
			// Catch all other exceptions
			logger.error(
					"NHB_NS_OO4 - Unexpected error occurred while sending nvrStatus change notification for nvrId: {}",
					nvrId, e);
		}
	}

	private void sendCameraStatusChangeNotification(String nvrId, String cctvId, String status) {
		try {
			// Log the request data being sent
			logger.info("NHB_NS_OO5 - Sending camStatus change notification for: {}",
					nvrId, cctvId, status);
			List<Map<String, Object>> cameraStatusList = new ArrayList<>();
			Map<String, Object> requestBody = new LinkedHashMap<>();
			requestBody.put("nvrId", nvrId);
			requestBody.put("cctvId", cctvId);
			requestBody.put("status", status);
			cameraStatusList.add(requestBody);
			logger.info("NHB_NS_OO5 - Sending camStatus change notification for: {}",
					cameraStatusList);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// Send the notification as a JSON payload
			HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(cameraStatusList, headers);
			logger.info("NHB_NS_OO4 - Sending request as notification: {}", request);

			// Send the POST request // cameraStatusList -> request
			ResponseEntity<String> response = restTemplate.postForEntity(camStatusChangeUrl, request,
					String.class);

			// Check if the response is successful
			if (response.getStatusCode().is2xxSuccessful()) {
				logger.info("NHB_NS_OO5 - camStatus change notification sent successfully for: {}", cameraStatusList);
			} else {
				// Log the response body and status code if it's not successful
				logger.warn("NHB_NS_OO5 - Failed to send camStatus change notification for: {}. Response: {}",
						cameraStatusList, response.getStatusCode());
			}

		} catch (HttpClientErrorException | HttpServerErrorException httpException) {
			// Log specific details about client/server-side errors
			logger.error(
					"NHB_NS_OO5 - HTTP error occurred while sending camStatus change notification for: {}. Error: {}",
					cctvId, httpException.getStatusCode(), httpException);

		} catch (ResourceAccessException timeoutException) {
			// Handle timeout exceptions
			logger.error("NHB_NS_OO5 - Timeout occurred while sending camStatus change notification for: {}", cctvId,
					timeoutException);

		} catch (Exception e) {
			// Catch all other exceptions
			logger.error("NHB_NS_OO5 - Unexpected error occurred while sending camStatus change notification for: {}",
					cctvId, e);
		}
	}

}