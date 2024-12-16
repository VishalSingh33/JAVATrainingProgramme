package com.nvr.heartbeat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nvr.heartbeat.controller.NvrController;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.service.NvrService;

import java.util.concurrent.*;

@SpringBootTest
class HeartbeatApplicationTests {

	private static final int TOTAL_REQUESTS = 5000; // Total number of requests
	private static final int THREADS = 500; // Number of concurrent threads
	private static final String URL_TEMPLATE = "http://localhost:8080/mcc/nvr/{nvrId}";

	private static ExecutorService executor;
	private static RestTemplate restTemplate;
	private static long startTime;

    @InjectMocks
    private NvrController nvrController;
    @Mock
    private NvrService nvrService;
    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUps() {
        MockitoAnnotations.openMocks(this);
        executor = Executors.newFixedThreadPool(50);  // Adjust thread pool size as needed
    }

	// Initialize resources before tests start
	@BeforeAll
	public static void setUp() {
		executor = Executors.newFixedThreadPool(THREADS);
		restTemplate = new RestTemplate();
		startTime = System.nanoTime();
	}

	// Define the load test case
	@Test
	public void testLoadNvrCameraStatus() {
		for (int i = 0; i < TOTAL_REQUESTS; i++) {
			String nvrId = String.format("2C:CF:67:61:56:%02X", i % 256);  // Create a unique nvrId for each request

			executor.submit(() -> {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json");

				// Create an optional request body, null in this case
				HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

				try {
					// Make the HTTP POST request to the endpoint
					ResponseEntity<?> response = restTemplate.exchange(URL_TEMPLATE, HttpMethod.POST, requestEntity,
							Object.class, nvrId);

					// Verify response status is 2xx
					assertTrue(response.getStatusCode().is2xxSuccessful(), "Expected 2xx successful status for NVR ID "
							+ nvrId + ", but got: " + response.getStatusCode());
				} catch (Exception e) {
					System.err.println("Error for NVR ID " + nvrId + ": " + e.getMessage());
				}
			});
		}
	}

	// Clean up resources after tests complete
	@AfterAll
	public static void tearDown() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(20, TimeUnit.MINUTES)) {
				System.err.println("Some tasks did not finish in the expected time.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Calculate and print duration
		long endTime = System.nanoTime();
		long duration = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
		System.out.println("Total execution time: " + duration + " ms");

		// Calculate throughput
		System.out.println("Throughput (requests/sec): " + (TOTAL_REQUESTS * 1000 / duration));
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    void testNvrCameraStatus_WithHighLoad_NullRequestBody() throws InterruptedException {
        // Mock NvrService response
        when(nvrService.nvrCameraStatus(anyString(), isNull(), any(BindingResult.class)))
            .thenReturn((Response) new Response<String>());

        CountDownLatch latch = new CountDownLatch(TOTAL_REQUESTS);
        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            String nvrId = String.format("2C:CF:67:61:56:%02X", i % 256);  // Generate valid NVR IDs

            executor.submit(() -> {
                try {
                    Response<?> response = nvrController.nvrCameraStatus(nvrId, null, bindingResult);
                    assertEquals(HttpStatus.OK.value(), response.getStatusCode(),
                            "Unexpected status for NVR ID " + nvrId);
                } catch (JsonProcessingException e) {
                    fail("JsonProcessingException occurred: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();  // Wait for all requests to finish
        executor.shutdown();
        assertTrue(executor.awaitTermination(1, TimeUnit.MINUTES), "Performance test did not complete in time");

        // Verify that the service was called for each request
        verify(nvrService, times(TOTAL_REQUESTS)).nvrCameraStatus(anyString(), isNull(), any(BindingResult.class));
    }
}