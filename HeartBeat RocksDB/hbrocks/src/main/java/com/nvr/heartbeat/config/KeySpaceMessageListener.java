// package com.nvr.heartbeat.config;

// import java.nio.charset.StandardCharsets;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.ConcurrentLinkedQueue;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.redis.connection.Message;
// import org.springframework.data.redis.connection.MessageListener;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Component;

// import com.nvr.heartbeat.service.HeartBeatService;
// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Component
// public class KeySpaceMessageListener implements MessageListener {

//     private final HeartBeatService heartbeatService;
//     private Logger logger = LoggerFactory.getLogger(this.getClass());

//     @Autowired
// 	@Qualifier("redisTemplate")
// 	private RedisTemplate<String, List<Map<String, Object>>> redisTemplate;

//     @SuppressWarnings({ "null" })
//     @Override
//     public void onMessage(Message message, byte[] pattern) {
        
//         // Extract the expired key from the message
//         String expiredApiKey = new String(message.getBody(), StandardCharsets.UTF_8);
//         String[] parts = expiredApiKey.split("#");
        
//         if (parts.length != 2) {
//             logger.warn("NHB_CONF_KL_01 - Invalid key: {}", expiredApiKey);
//             return;
//         }
//         String deviceId = parts[0];
//         String apiKey = parts[1];
//         logger.info("NHB_CONF_KL_01 - Parsed API Key: {}, Device ID: {}", apiKey, deviceId);

//         // Notify via webhook asynchronously with retries
//         CompletableFuture.runAsync(() -> {
//             for (int retryCount = 0; retryCount < 3; retryCount++) {
//                 try {
//                     heartbeatService.sendStatusNotification(deviceId, apiKey, "INACTIVE");
//                     return; // Exit the loop on success
//                 } catch (Exception e) {
//                     logger.error("NHB_CONF_KL_02 - Error sending status notification. Retry {} of 3", retryCount + 1, e);
//                 }
//             }
//             logger.error("NHB_CONF_KL_02 - Failed to send status notification after 3 retries for key: {}", expiredApiKey);
//         });
//     }
// }