package com.geektrust.backend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.entites.TrainObject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BogieService implements IBogieService {

    private Map<String, Integer> stationAfterHYB;
     private List<TrainObject> deptTrain;
    private String dataInput;

    public BogieService(String dataInput) {
        this.dataInput = dataInput;
        this.stationAfterHYB = new HashMap<>();
        this.stationAfterHYB.put("HYB", 0);
        this.stationAfterHYB.put("NGP", 400);
        this.stationAfterHYB.put("ITJ", 700);
        this.stationAfterHYB.put("BPL", 800);
        this.stationAfterHYB.put("AGA", 2500);
        this.stationAfterHYB.put("NDL", 2700);
        this.stationAfterHYB.put("PTA", 3800);
        this.stationAfterHYB.put("NJP", 4200);
        this.stationAfterHYB.put("GHY", 4700);
        this.deptTrain = new ArrayList<>();
    }

    @Override
    public List<TrainObject> printTrainA(String[] input) {

        List<String> arrA = List.of("ARRIVAL", "TRAIN_A", "ENGINE");
        List<String> arr = new ArrayList<>();
        List<TrainObject> tempBoggie = new ArrayList<>();
        
        for (int i = 2; i < input.length; i++) {
            if (this.stationAfterHYB.containsKey(input[i].trim())) {
                arr.add(input[i]);
                TrainObject trainObj = new TrainObject(input[i].trim(), stationAfterHYB.get(input[i].trim()));
                tempBoggie.add(trainObj);
            }
        }
        List<String> boggieList = new ArrayList<>(arrA);
        boggieList.addAll(arr);
        String boggie = String.join(" ", boggieList);
        String result = boggie.replace(",", " ");
        System.out.println(result.trim());

        return tempBoggie;
    }

    @Override
    public List<TrainObject> printTrainB(String[] input) {

        List<String> arrA = List.of("ARRIVAL", "TRAIN_B", "ENGINE");
        List<String> arr = new ArrayList<>();
        List<TrainObject> tempBoggie = new ArrayList<>();
        
        for (int i = 2; i < input.length; i++) {
            if (stationAfterHYB.containsKey(input[i].trim())) {
                arr.add(input[i]);
                TrainObject trainObj = new TrainObject(input[i].trim(), stationAfterHYB.get(input[i].trim()));
                tempBoggie.add(trainObj);
            }
        }
        List<String> boggieList = new ArrayList<>(arrA);
        boggieList.addAll(arr);
        String boggie = String.join(" ", boggieList);
        String result = boggie.replace(",", " ");
        System.out.println(result.trim());

        return tempBoggie;
    }

    List<String> trainABogie = Arrays.asList("ENGINE", "CHENNAI", "SALEM", "BANGALORE", "KURNOOL",
            "HYDERABAD", "NAGPUR", "ITARSI", "BHOPAL", "AGRA", "NEW DELHI");

    List<String> trainBBogie = Arrays.asList("ENGINE", "TRIVANDRUM", "SHORANUR", "MANGALORE", "MADGAON",
            "PUNE", "HYDERABAD", "NAGPUR", "ITARSI", "BHOPAL", "PATNA", "NEW JALPAIGURI", "GUWAHATI");

    // @Override
    // public ResponseEntity<Bogie> addBogie(Train train, List<Bogie> bogies) {

    //     Train existingTrain = trainRepository.findTrainByName(train.getTrainName()); // Implement findTrainById method.

    //     if (existingTrain == null) {
    //         // return new ResponseEntity<>(HttpStatus.NOT_FOUND); //// Return 404 if the
    //         // train is not found.
    //         throw new TrainNotFoundException("No Train with this nameId");
    //     }
    //     // Add the bogies to the train.
    //     if (existingTrain.getTrainName() == TrainName.A) {
    //         existingTrain.getBogies().addAll(trainABogie);

    //     } else if (existingTrain.getTrainName() == TrainName.A) {
    //         existingTrain.getBogies().addAll(trainBBogie);
    //     }

    //     // You can perform any other necessary operations here.
    //     return new ResponseEntity<>(HttpStatus.CREATED);

    // }

}
