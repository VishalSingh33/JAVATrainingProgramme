package com.geektrust.backend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.backend.entites.TrainObject;

public class TrainMergerService implements ITrainMergerService {

    private final String dataInput;
    private Map<String, Integer> stationAfterHYB;
    private BogieService bogieService;
    private List<TrainObject> deptTrain1;
    private List<TrainObject> deptTrain2;
    private List<TrainObject> deptTrain;

    private final static String[] STATION_ORDER = { "HYB", "NGP", "ITJ", "BPL", "AGA", "NDL", "PTA", "NJP", "GHY" };

    public TrainMergerService(String dataInput) {

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
        this.deptTrain1 = new ArrayList<>();
        this.deptTrain2 = new ArrayList<>();
        this.deptTrain = new ArrayList<>();
    }

    @Override
    public void trainMerge() {
        String[] inputLines = dataInput.split("\n");

        for (String inputLine : inputLines) {
            inputLine = inputLine.trim();
            if (!inputLine.trim().isEmpty()) {
                String[] input = inputLine.split("\\s+");
                switch (input[0]) {
                    case "TRAIN_A":
                        deptTrain1 = bogieService.printTrainA(input);
                        break;
                    case "TRAIN_B":
                        deptTrain2 = bogieService.printTrainB(input);
                        break;
                }
            }
        }
        deptTrain.addAll(deptTrain1);
        deptTrain.addAll(deptTrain2);
        Collections.sort(deptTrain, (a, b) -> {
            int idA = getStationOrder(a);
            int idB = getStationOrder(b);
            return idB - idA;
        });

        List<String> boggieListToArray = new ArrayList<>();
        for (TrainObject train : deptTrain) {
            if (train.getId() != 0) {
                boggieListToArray.add(train.getName());
            }
        }

        List<String> startBoggieList = Arrays.asList("DEPARTURE", "TRAIN_AB", "ENGINE", "ENGINE");
        List<String> boggieList = new ArrayList<>(startBoggieList);
        boggieList.addAll(boggieListToArray);
        String boggie = String.join(" ", boggieList);
        String result = boggie.replace("HYB", "");
        System.out.println(result.trim());
    }

    private int getStationOrder(Object a) {
        for (int i = 0; i < STATION_ORDER.length; i++) {
            if (a.equals(STATION_ORDER[i])) {
                return i;
            }
        }
        return -1;
    }

}