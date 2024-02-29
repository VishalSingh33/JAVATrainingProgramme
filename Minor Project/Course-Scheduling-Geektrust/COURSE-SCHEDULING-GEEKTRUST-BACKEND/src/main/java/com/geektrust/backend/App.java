package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.controller.ControllerInvoker;
import com.geektrust.backend.exceptions.NoSuchControllerException;

 // To run the application  ./gradlew run --args="INPUT_FILE=sample_input/input1.txt"

public class App {
    public static void run(List<String> controllerLineArgs){
        ApplicationConfig applicationConfig = new ApplicationConfig();       
        ControllerInvoker controllerInvoker = applicationConfig.getControllerInvoker();
        BufferedReader reader;

        String inputFile = controllerLineArgs.get(0).split("=")[1];
        controllerLineArgs.remove(0);
        //System.out.println(ControllerLineArgs);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                controllerInvoker.executeController(tokens.get(0),tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | NoSuchControllerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        List<String> ControllerLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = ControllerLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        //System.out.println(actualSequence);
        if(expectedSequence.equals(actualSequence)){
            run(ControllerLineArgs);
        }

          
    }
}