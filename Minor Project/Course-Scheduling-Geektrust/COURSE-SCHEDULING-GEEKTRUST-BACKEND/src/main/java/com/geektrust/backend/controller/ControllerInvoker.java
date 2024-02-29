package com.geektrust.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.backend.exceptions.NoSuchControllerException;

public class ControllerInvoker {
    private static final Map<String, IController> controllerMap = new HashMap<>();

    // Register the Controller into the HashMap
    public void register(String controllerName, IController controller){
        controllerMap.put(controllerName, controller);
    }

    // Get the registered Controller
    private IController get(String controllerName){
        return controllerMap.get(controllerName);
    }

    // Execute the registered Controller
    public void executeController(String controllerName, List<String> tokens) throws NoSuchControllerException {
        IController controller = get(controllerName);
        try {
            if(controller != null){
                controller.execute(tokens);
            }
        } catch (NoSuchControllerException e) {
            System.out.println("No Such Controller Found");
        }
        
    
    }

}