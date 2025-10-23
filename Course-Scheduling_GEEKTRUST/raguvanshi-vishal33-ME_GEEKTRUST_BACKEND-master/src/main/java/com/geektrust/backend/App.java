package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
// import com.geektrust.backend.controller.CommandInvoker;
import com.geektrust.backend.controller.CommandRegistry;

public class App {

	public static void main(String[] args) {
		// if (args.length != 1){
        //     throw new RuntimeException();
        // }
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        commandLineArgs.add("INPUT-FILE=input1.txt");
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining(","));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }

        // run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs){

        ApplicationConfig conf = ApplicationConfig.getInstance();
        // ApplicationConfig conf = new ApplicationConfig();
		CommandRegistry commandInvoker = conf.getCommandInvoker();
		// String inputFile = commandLineArgs.get(0);
        String inputFile = commandLineArgs.get(0).split("=")[1];

        // try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
		// 	String line = reader.readLine();
		// 	while (line != null) {
		// 		List<String> tokens = Arrays.stream(line.split(" ")).collect(Collectors.toList());
		// 		// commandInvoker.executecommand(tokens.get(0), tokens);
		// 		line = reader.readLine();
		// 	}
		// 	reader.close();
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            while (true) {
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                commandInvoker.invokeCommand(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}