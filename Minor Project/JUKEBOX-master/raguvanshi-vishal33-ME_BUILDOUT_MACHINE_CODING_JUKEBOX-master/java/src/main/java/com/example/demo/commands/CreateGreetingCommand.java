package com.example.demo.commands;

import java.util.List;

import com.example.demo.entities.Greeting;
import com.example.demo.services.GreetingService;

public class CreateGreetingCommand implements ICommand{

    private final GreetingService greetingService; 

    public CreateGreetingCommand(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String message = tokens.get(1);
        Greeting createGreet = greetingService.create(message);
        System.out.println(createGreet);
    }
    
}
