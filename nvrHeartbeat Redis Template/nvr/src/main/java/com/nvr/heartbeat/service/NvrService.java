package com.nvr.heartbeat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvr.heartbeat.entites.Camera;
import com.nvr.heartbeat.entites.NvrCamera;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.helper.ResponseCode;
import com.nvr.heartbeat.helper.ResponseHelper;

@Service
public class NvrService {

	@Autowired
	private RedisTemplate<String, List<Map<String, Object>>> redisTemplate;

	@Autowired
	private RestTemplate restTemplate;

	Response<?> response = null;

	Response<List<Map<String, Object>>> responses = null;

	private ObjectMapper mapper = new ObjectMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String ACTIVE = "Active";
	private static final String INACTIVE = "Inactive";
	private static final long INACTIVITY_THRESHOLD_MS = 120000;
	private static final String NVR_STATUS_CHANGE_URL = "https://vms.mycloudcam.cloud/vsaas/v1.0/nvr/status/change";
	private static final String CAM_STATUS_CHANGE_URL = "https://vms.mycloudcam.cloud/vsaas/v1.0/cctv/status/change";

	//// NVR GET LIVE STATUS
	public Response<?> nvrLiveStatus(List<String> nvrIds) {

		logger.info("NHB_NS_OO1 - reqParams:{}" + nvrIds);
		Map<String, Object> nvrStatuses = new ConcurrentHashMap<>();
		// Executor for async tasks
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<CompletableFuture<Void>> futures = new ArrayList<>();

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
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
				logger.warn("NHB_NS_OO1 - Executor shutdown forced due to timeout.");
			}
		} catch (InterruptedException e) {
			logger.error("NHB_NS_OO1 - Interrupted while waiting for executor shutdown.", e);
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
		List<Map<String, Object>> responseList = new ArrayList<>();
		String stat = null;
		for (Map.Entry<String, Object> entry : nvrStatuses.entrySet()) {
			Map<String, Object> map = new LinkedHashMap<>(); // Create a new map for each entry
			String nvrId = entry.getKey();
			List<Map<String, Object>> existingData = redisTemplate.opsForValue().get(nvrId);

			if (existingData != null) {
				for (Map<String, Object> dataMap : existingData) {
					// Check if the map contains the 'status' key
					if (dataMap.containsKey("status")) {
						// Retrieve and print the status value
						stat = (String) dataMap.get("status");

						if ("Active".equalsIgnoreCase(stat)) {
							map.put("nvrId", nvrId);
							map.put("status", stat);
							map.put("statusCode", 200);
							map.put("message", "Active");
							responseList.add(map);
						} else if ("Inactive".equalsIgnoreCase(stat)) {
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
				map.put("status", stat);
				map.put("statusCode", 404);
				map.put("message", "NVR not Found");
				responseList.add(map);
			}
		}
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO1 - NVR statuses: {}", responseList);
		return responses;
	}

	//// nvr Heartbeat with camerList
	@SuppressWarnings("unchecked")
	public Response<?> nvrCameraStatus(String nvrId, String request, BindingResult bindingResult) {

		logger.info("NHB_NS_OO2 - handleCameraStatusChange nvrId: {}", nvrId + request);

		List<Map<String, Object>> cameraList = new ArrayList<>();
		Map<String, Object> mapNvr = new LinkedHashMap<>();
		List<Map<String, Object>> responseList = new ArrayList<>();
		// Use a map for efficient camera lookup during merging
		Map<String, Map<String, Object>> cameraMap = new LinkedHashMap<>();
		NvrCamera nvrRequest = null;
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
				}
			}
			// Request is a single JSON object
			if (request != null && !"".equals(request) && !request.isBlank()) {
				nvrRequest = mapper.readValue(request, NvrCamera.class);
				logger.info("NHB_NS_OO2 - JSON object: {}", nvrRequest.toString());

				// Now you can access the list of cameras from the request
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
						// add a restTemplate for camStatus change
						sendCameraStatusChangeNotification(nvrId, cam.getCctvId(), cam.getStatus()); // check this line
					}
				}
			}
			// Rebuild the merged camera list
			cameraList.addAll(cameraMap.values());

		} catch (JsonProcessingException e) {
			logger.error("NHB_NS_OO2 - error - " + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND,
					"An error occurred while converting string to JSON");
		} finally {
			logger.info("NHB_NS_OO2 - End");
		}
		// Build the final NVR data
		mapNvr.put("nvrId", nvrId);
		mapNvr.put("status", ACTIVE);
		mapNvr.put("timestamp", System.currentTimeMillis());
		if (!cameraList.isEmpty()) {
			mapNvr.put("cameraList", cameraList);
		}
		responseList.add(mapNvr);
		// Store the updated NVR data back into Redis
		redisTemplate.opsForValue().set(nvrId, responseList);

		// Log and send notification if status is ACTIVE
		logger.info("NHB_NS_OO2 - nvrId : {}", nvrId, responseList, LocalDateTime.now());

		// Deserialize JSON string into a list of maps (only if it's not null)
		if (responseList != null) {
			for (Map<String, Object> statusData : responseList) {
				String status = (String) statusData.get("status");
				logger.info("NHB_NS_OO2 - nvrId: {} as status: {}", nvrId, status);
				// Check if the current status is null or "Active"
				if (status == ACTIVE || "Active".equals(status)) {
					sendNvrStatusChangeNotification(nvrId, ACTIVE); // check this line
				}
			}
		}
		// Return the merged response
		Response<List<Map<String, Object>>> responses = ResponseHelper.getSuccessResponse(responseList);
		logger.info("NHB_NS_OO2 - NVR statuses: {}", responseList);
		return ResponseHelper.getSuccessResponse(null); // return responses;
	}

	@Scheduled(cron = "0 */2 * * * ?")
	public void checkInactiveNvrIds() {
		// Fetch all NVR IDs from Redis
		Set<String> nvrIds = redisTemplate.keys("*");
		logger.info("NHB_NS_OO3 - Checking NVR IDs: {}", nvrIds);

		// Iterate over each NVR ID
		for (String nvrId : nvrIds) {
			List<Map<String, Object>> existingData = redisTemplate.opsForValue().get(nvrId);
			logger.info("NHB_NS_OO3 - Processing data for NVR ID: {}", existingData);

			// Check if data exists for the NVR ID
			if (existingData != null && !existingData.isEmpty()) {
				boolean isInactive = false;
				Long lastHeartbeatTimestamp = null;

				// Iterate over the list of entries and extract the latest timestamp
				for (Map<String, Object> nvrEntry : existingData) {
					lastHeartbeatTimestamp = (Long) nvrEntry.get("timestamp");
					String status = (String) nvrEntry.get("status");
					String nvrIdExtracted = (String) nvrEntry.get("nvrId");

					logger.info("NHB_NS_OO3 - nvrId: {}, Status: {}, lastHeartbeatTimestamp: {}", nvrIdExtracted, status,
							lastHeartbeatTimestamp);

					// Check if the NVR has been inactive for longer than the threshold
					long currentTime = System.currentTimeMillis();
					if (lastHeartbeatTimestamp != null
							&& (currentTime - lastHeartbeatTimestamp > INACTIVITY_THRESHOLD_MS)) {
						isInactive = true;
						logger.info("NHB_NS_OO3 - NVR ID: {} inactive for over {} ms", nvrId, INACTIVITY_THRESHOLD_MS);
					}
				}
				// Process NVR if inactive
				if (isInactive) {
					CompletableFuture.runAsync(() -> processNvrIds(nvrId));
				}
			} else {
				logger.info("NHB_NS_OO3 - No nvrId in Rest: {}", nvrId);
			}
		}
	}

	// Process the NVR IDs to check their status and mark inactive if needed
	private void processNvrIds(String nvrId) {
		if (nvrId == null || nvrId.isEmpty())
			return;

		redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
			// Get TTL (time-to-live) in seconds
			Long ttl = redisTemplate.getExpire(nvrId, TimeUnit.SECONDS);
			if (ttl != null && ttl <= 0) { // TTL expired
				List<Map<String, Object>> jsonStatus = redisTemplate.opsForValue().get(nvrId);

				// Deserialize JSON string into a list of maps (only if it's not null)
				if (jsonStatus != null) {
					for (Map<String, Object> statusData : jsonStatus) {
						String status = (String) statusData.get("status");
						logger.info("NHB_NS_OO3 - Marked NVR ID: {} as status: {}", nvrId, status);
						// Check if the current status is null or "Active"
						if (status == null || "Active".equals(status)) {
							// Set the NVR status to "Inactive"
							statusData.put("status", INACTIVE);
							// Log the change
							logger.info("NHB_NS_OO3 - Marked NVR ID: {} as INACTIVE", nvrId);
							// Send status change notification to external service
							sendNvrStatusChangeNotification(nvrId, INACTIVE); // check this line
						}
					}
					// After updating the status, store the updated list back to Redis
					redisTemplate.opsForValue().set(nvrId, jsonStatus);
				}
			}
			return null;
		});
	}

	private void sendNvrStatusChangeNotification(String nvrId, String status) {
		try {
			List<Map<String, Object>> nvrStatusList = new ArrayList<>();
			Map<String, Object> requestBody = new LinkedHashMap<>();
			requestBody.put("nvrId", nvrId);
			requestBody.put("status", status);
			nvrStatusList.add(requestBody);
			logger.info("NHB_NS_OO4 - Sending nvrStatus change notification for: {}", nvrStatusList);

			ResponseEntity<String> response = restTemplate.postForEntity(NVR_STATUS_CHANGE_URL, nvrStatusList,
					String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				logger.info("NHB_NS_OO4 - NVR status change notification sent successfully for nvrId: {} with status: {}", nvrId,
						status);
			} else {
				logger.warn("NHB_NS_OO4 - Failed to send nvrStatus change notification for nvrId: {}", nvrId);
			}
		} catch (HttpClientErrorException | HttpServerErrorException httpException) {
			// Log specific details about client/server-side errors
			logger.error("NHB_NS_OO4 - HTTP error occurred while sending nvrStatus change notification for nvrId: {}. Error: {}",
					nvrId, httpException.getStatusCode(), httpException);

		} catch (ResourceAccessException timeoutException) {
			// Handle timeout exceptions
			logger.error("NHB_NS_OO4 - Timeout occurred while sending nvrStatus change notification for nvrId: {}", nvrId,
					timeoutException);

		} catch (Exception e) {
			// Catch all other exceptions
			logger.error("NHB_NS_OO4 - Unexpected error occurred while sending nvrStatus change notification for nvrId: {}", nvrId,
					e);
		}
	}

	private void sendCameraStatusChangeNotification(String nvrId, String cctvId, String status) {
		try {
			// Log the request data being sent
			logger.info("NHB_NS_OO5 - Sending camStatus change notification for: {}", nvrId, cctvId, status);
			List<Map<String, Object>> cameraStatusList = new ArrayList<>();
			Map<String, Object> requestBody = new LinkedHashMap<>();
			requestBody.put("nvrId", nvrId);
			requestBody.put("cctvId", cctvId);
			requestBody.put("status", status);
			cameraStatusList.add(requestBody);
			logger.info("NHB_NS_OO5 - Sending camStatus change notification for: {}", cameraStatusList);
			// Send the POST request
			ResponseEntity<String> response = restTemplate.postForEntity(CAM_STATUS_CHANGE_URL, cameraStatusList,
					String.class);

			// Check if the response is successful
			if (response.getStatusCode().is2xxSuccessful()) {
				logger.info("NHB_NS_OO5 - camStatus change notification sent successfully for: {}", cameraStatusList);
			} else {
				// Log the response body and status code if it's not successful
				logger.warn("NHB_NS_OO5 - Failed to send camStatus change notification for: {}. Response: {}", cameraStatusList,
						response.getStatusCode());
			}

		} catch (HttpClientErrorException | HttpServerErrorException httpException) {
			// Log specific details about client/server-side errors
			logger.error("NHB_NS_OO5 - HTTP error occurred while sending camStatus change notification for: {}. Error: {}",
					cctvId, httpException.getStatusCode(), httpException);

		} catch (ResourceAccessException timeoutException) {
			// Handle timeout exceptions
			logger.error("NHB_NS_OO5 - Timeout occurred while sending camStatus change notification for: {}", cctvId,
					timeoutException);

		} catch (Exception e) {
			// Catch all other exceptions
			logger.error("NHB_NS_OO5 - Unexpected error occurred while sending camStatus change notification for: {}", cctvId,
					e);
		}
	}

}