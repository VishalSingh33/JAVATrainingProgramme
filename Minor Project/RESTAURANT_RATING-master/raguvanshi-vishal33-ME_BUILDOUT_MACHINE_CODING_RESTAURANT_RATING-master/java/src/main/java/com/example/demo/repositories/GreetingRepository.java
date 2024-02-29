package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entities.Greeting;

public class GreetingRepository implements IGreetingRepository {

    private final Map<Long,Greeting> greetingMap;
    private Long autoIncrement = 1L;

    public GreetingRepository(){
        greetingMap = new HashMap<Long,Greeting>();
    }

    @Override
    public Greeting save(Greeting greeting) {
        Greeting g = new Greeting(greeting.getMessage(),autoIncrement);
        greetingMap.put(autoIncrement,g);
        ++autoIncrement;
        return g;
    }

    @Override
    public boolean existsById(Long id) {
        return greetingMap.containsKey(id);
    }

    @Override
    public Optional<Greeting> findById(Long id) {
        return Optional.ofNullable(greetingMap.get(id));
    }

    @Override
    public List<Greeting> findAll() {
        return greetingMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        greetingMap.remove(id);
    }

    @Override
    public long count() {
        return greetingMap.size();
    }
    
}
