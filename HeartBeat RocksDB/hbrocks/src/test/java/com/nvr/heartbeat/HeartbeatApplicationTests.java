package com.nvr.heartbeat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rocksdb.RocksDB;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import com.nvr.heartbeat.dto.RegistrationIdDto;

import java.util.Map;
import java.util.concurrent.*;

@SpringBootTest
@AutoConfigureMockMvc
class HeartbeatApplicationTests {

    @MockBean
    private RocksDB rocksDB;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int TOTAL_REQUESTS = 100;
    private static final int THREADS = 50;
    private static final String URL_TEMPLATE = "http://localhost:8080/mcc/device/register";

    private static ExecutorService executor;
    private static RestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        executor = Executors.newFixedThreadPool(THREADS);
        restTemplate = new RestTemplate();
    }

    // Example mock behavior
    @BeforeEach
    void mockRocksDB() throws Exception {
        Mockito.when(rocksDB.get(Mockito.any(byte[].class))).thenReturn("mockValue".getBytes());
        Mockito.doNothing().when(rocksDB).put(Mockito.any(byte[].class), Mockito.any(byte[].class));
    }

    @AfterAll
    public static void tearDown() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    @Test
    public void testLoadNvrCameraStatus() {
        try {
            for (int i = 0; i < TOTAL_REQUESTS; i++) {
                String deviceId = String.format("2C:CF:67:%02X:%02X:%02X", (i / 65536) % 256, (i / 256) % 256, i % 256);

                Map<String, String> apiKeyMap = Map.of(
                        // "9d8f5715-2e7c-4e64-8e34-35f510c1266", "Skypro-IPTV",
                        "550e8400-e29b-41d4-a716-446655440000", "Pionner-Pisho"
                        // "acde070d-8c4c-4f0d-9d8a-162843c10333", "Skypro-Vsas"
                        );

                for (Map.Entry<String, String> entry : apiKeyMap.entrySet()) {
                    RegistrationIdDto regDto = new RegistrationIdDto(entry.getKey(), deviceId, entry.getValue());

                    executor.submit(() -> {
                        try {
                            HttpHeaders headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            HttpEntity<RegistrationIdDto> requestEntity = new HttpEntity<>(regDto, headers);

                            ResponseEntity<?> response = restTemplate.exchange(URL_TEMPLATE, HttpMethod.POST,
                                    requestEntity, Object.class);

                            assertTrue(response.getStatusCode().is2xxSuccessful(), "Failed for deviceId: "
                                    + regDto.getDeviceId() + ", Status: " + response.getStatusCode());

                            logger.info("Success for deviceId: {}", regDto.getDeviceId());
                        } catch (Exception e) {
                            logger.error("Error for deviceId: {}", regDto.getDeviceId(), e);
                        }
                    });
                }
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    logger.error("Executor forcibly shut down.");
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
                logger.error("Executor interrupted during shutdown.", e);
            }
        }
    }

}