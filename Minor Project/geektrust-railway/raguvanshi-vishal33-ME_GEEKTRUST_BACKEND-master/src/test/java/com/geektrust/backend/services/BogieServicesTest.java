package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
// import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.geektrust.backend.entites.TrainObject;

@ExtendWith(MockitoExtension.class)
public class BogieServicesTest {

    @Mock
    private Map<String, Integer> stationAfterHYB;

    @Mock
    private BogieService bogieService;

    @Test
    @MockitoSettings()
    void testPrintTrainA() {
        when(stationAfterHYB.containsKey(any(String.class))).thenReturn(true);

        String[] input = {"TRAIN_A", "ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR"};

        List<String> expectedResultA = Arrays.asList("ARRIVAL", "TRAIN_A", "ENGINE", "HYB", "NGP", "BPL");

        // List<TrainObject> resultA = bogieService.printTrainA(input);
        // assertEquals(expectedResultA, resultA);

        // Act
        List<TrainObject> resultA = bogieService.printTrainA(input);
        // Assert
        assertEquals(expectedResultA.size(), resultA.size());
        for (int i = 0; i < expectedResultA.size(); i++) {
            assertEquals(expectedResultA.get(i), resultA.get(i).getName());
        }
    }

    @Test
    @MockitoSettings()
    void testPrintTrainB() {
        when(stationAfterHYB.containsKey(any(String.class))).thenReturn(true);

        String[] input = {"TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA"};

        List<String> expectedResultB = Arrays.asList("ARRIVAL TRAIN_B ENGINE", "PTA", "HYB", "BPL", "ITJ", "SRR",
                "NJP");

        // Act
        List<TrainObject> resultB = bogieService.printTrainB(input);

        // Assert
        // assertEquals(expectedResultB, resultB);
        assertEquals(expectedResultB.size(), resultB.size());
        for (int i = 0; i < expectedResultB.size(); i++) {
            assertEquals(expectedResultB.get(i), resultB.get(i).getName());
        }
    }

     @Test
     void testTrainMergerService() {
        // Mocking necessary dependencies
        BogieService bogieService = mock(BogieService.class);

        List<TrainObject> trainAExpectedResult = Arrays.asList(
                new TrainObject("HYB", 0),
                new TrainObject("NGP", 400),
                new TrainObject("ITJ", 700)
        );
        when(bogieService.printTrainA(new String[]{"TRAIN_A", "ENGINE", "SLM", "BLR", "KRN", "HYB", "SLM", "NGP", "ITJ"}))
                .thenReturn(trainAExpectedResult);

        List<TrainObject> trainBExpectedResult = Arrays.asList(
                new TrainObject("NJP", 4200),
                new TrainObject("PTA", 3800)
        );
        when(bogieService.printTrainB(new String[]{"TRAIN_B", "ENGINE", "SRR", "MAO", "NJP", "PNE", "PTA"}))
                .thenReturn(trainBExpectedResult);
        // Create TrainMergerService and inject dependencies
        TrainMergerService trainMergerService = new TrainMergerService("Sample input data");
        trainMergerService.setBogieService(bogieService);

        // Expected output
        String expectedOutput = "DEPARTURE TRAIN_AB ENGINE ENGINE NJP PTA ITJ NGP";
        // Act
        String actualOutput = trainMergerService.trainMerge();
        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

}
