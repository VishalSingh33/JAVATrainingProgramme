package com.example.demo.commands;

import java.util.List;

import com.example.demo.entities.Greeting;
import com.example.demo.services.GreetingService;

public class GetGreetingCommand implements ICommand{
    
    private final GreetingService greetingService; 

    public GetGreetingCommand(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long id = Long.parseLong(tokens.get(1));
        try {
            Greeting getGreet = greetingService.getGreeting(id);
            System.out.println(getGreet);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
