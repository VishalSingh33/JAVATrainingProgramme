//package com.nvr.heartbeatServiceTest;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.HostAndPort;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//public class HeartbeatApplicationTests {
//    private static final int THREADS = 50;        // Number of concurrent threads
//    private static final int OPERATIONS = 1000;   // Number of operations per thread
//
//    public static void main(String[] args) {
//        // Define Redis cluster nodes
//        Set<HostAndPort> redisNodes = new HashSet<>();
//        redisNodes.add(new HostAndPort("127.0.0.1", 6380)); // Replace with your Redis node IPs and ports
//        redisNodes.add(new HostAndPort("127.0.0.1", 6381));
//        redisNodes.add(new HostAndPort("127.0.0.1", 6382));
//
//        // Connect to Redis cluster
//        try (JedisCluster jedisCluster = new JedisCluster(redisNodes)) {
//            // Create a thread pool
//            ExecutorService executor = Executors.newFixedThreadPool(THREADS);
//
//            // Start time
//            long startTime = System.nanoTime();
//
//            // Submit tasks to the thread pool
//            for (int i = 0; i < THREADS; i++) {
//                executor.submit(() -> {
//                    RestTemplate restTemplate = new RestTemplate();
//                    String url = "http://localhost:8080/status"; // Replace with actual server URL
//
//                    // Prepare sample list of NVR IDs to send in each request
//                    List<String> nvrIds = new ArrayList<>();
//                    for (int j = 0; j < 10; j++) {  // Adding dummy NVR IDs
//                        nvrIds.add("NVR" + j);
//                    }
//
//                    HttpHeaders headers = new HttpHeaders();
//                    HttpEntity<List<String>> requestEntity = new HttpEntity<>(nvrIds, headers);
//
//                    for (int j = 0; j < OPERATIONS; j++) {
//                        // Make HTTP POST request to the /status endpoint
//                        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
//
//                        // Simulate Redis operation (GET and SET) per call to /status
//                        String key = "nvrStatusKey" + j;
//                        jedisCluster.set(key, "someStatusValue");
//                        jedisCluster.get(key);
//
//                        // Print response for debugging purposes (optional, can remove for actual load test)
//                        System.out.println(response.getBody());
//                    }
//                });
//            }
//
//            // Shut down the executor and await termination
//            executor.shutdown();
//            executor.awaitTermination(10, TimeUnit.MINUTES);
//
//            // End time and calculate duration
//            long endTime = System.nanoTime();
//            long duration = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
//            System.out.println("Total execution time: " + duration + " ms");
//
//            long totalOps = THREADS * OPERATIONS;
//            System.out.println("Throughput (ops/sec): " + (totalOps * 1000 / duration));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
