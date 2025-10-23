package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Greeting;
import com.example.demo.repositories.IGreetingRepository;

public class GreetingService{

    private final IGreetingRepository greetingRepository;

    public GreetingService(IGreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }
    
    public Greeting create(String message){
        Greeting g = new Greeting(message);
        return greetingRepository.save(g);
    }

    public List<Greeting> getAllGreetings(){
        return greetingRepository.findAll();
    }

    public Greeting getGreeting(Long id) {
        return greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting with id: " + id + " not found!"));
    }

}
