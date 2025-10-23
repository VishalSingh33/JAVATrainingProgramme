package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.geektrust.backend.services.BogieService;
import com.geektrust.backend.services.TrainMergerService;

public class App {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Geektrust Backend Challenge!");

        // String input1 = "TRAIN_A ENGINE BLR AGA BLR HYB ITJ BPL";
        String[] input1 = {"TRAIN_A", "ENGINE", "BLR", "AGA", "BLR", "HYB", "ITJ", "BPL"};
        // String input2 = "TRAIN_B ENGINE PTA HYB BPL ITJ SRR NJP";
        String[] input2 = {"TRAIN_B", "ENGINE", "PTA", "HYB", "BPL", "ITJ", "SRR", "NJP"};


        TrainMergerService trainMergerService1 = new TrainMergerService(input1.toString());
        trainMergerService1.trainMerge();

        TrainMergerService trainMergerService2 = new TrainMergerService(input2.toString());
        trainMergerService2.trainMerge();
    }
}
