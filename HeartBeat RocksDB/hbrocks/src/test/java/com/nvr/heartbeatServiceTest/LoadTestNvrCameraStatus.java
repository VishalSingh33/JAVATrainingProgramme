// package com.nvr.heartbeatServiceTest;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.client.RestTemplate;

// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.TimeUnit;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// public class LoadTestNvrCameraStatus {

//     private static final int TOTAL_REQUESTS = 25000; // Total number of requests
//     private static final int THREADS = 500;          // Number of concurrent threads
//     private static final String URL_TEMPLATE = "http://localhost:8080/mcc/nvr/{nvrId}";

//     private static ExecutorService executor;
//     private static RestTemplate restTemplate;
//     private static long startTime;

//     @BeforeAll
//     public static void setUp() {
//         executor = Executors.newFixedThreadPool(THREADS);
//         restTemplate = new RestTemplate();
//         startTime = System.nanoTime();
//     }

//     @Test
//     public void testLoadNvrCameraStatus() {
//         for (int i = 0; i < TOTAL_REQUESTS; i++) {
//             String nvrId = "NVR" + i;  // Create a unique nvrId for each request

//             executor.submit(() -> {
//                 HttpHeaders headers = new HttpHeaders();
//                 headers.add("Content-Type", "application/json");

//                 // Create an optional request body, null in this case
//                 HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

//                 try {
//                     // Make the HTTP POST request to the endpoint
//                     ResponseEntity<?> response = restTemplate.exchange(URL_TEMPLATE, HttpMethod.POST, requestEntity, Object.class, nvrId);

//                     // Verify response status is 2xx
//                     assertTrue(response.getStatusCode().is2xxSuccessful(), 
//                                "Expected 2xx successful status for NVR ID " + nvrId + ", but got: " + response.getStatusCode());
//                 } catch (Exception e) {
//                     System.err.println("Error for NVR ID " + nvrId + ": " + e.getMessage());
//                 }
//             });
//         }
//     }

//     @AfterAll
//     public static void tearDown() {
//         executor.shutdown();
//         try {
//             if (!executor.awaitTermination(20, TimeUnit.MINUTES)) {
//                 System.err.println("Some tasks did not finish in the expected time.");
//             }
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Calculate and print duration
//         long endTime = System.nanoTime();
//         long duration = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
//         System.out.println("Total execution time: " + duration + " ms");

//         // Calculate throughput
//         System.out.println("Throughput (requests/sec): " + (TOTAL_REQUESTS * 1000 / duration));
//     }
// }
