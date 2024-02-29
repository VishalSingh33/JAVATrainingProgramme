package com.example.demo.commands;

import java.util.List;

import com.example.demo.entities.Greeting;
import com.example.demo.services.GreetingService;

public class ListGreetingCommand implements ICommand{
    
    private final GreetingService greetingService; 

    public ListGreetingCommand(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void invoke(List<String> tokens) {
        List<Greeting> glist = greetingService.getAllGreetings();
        System.out.println(glist);
    }

}
