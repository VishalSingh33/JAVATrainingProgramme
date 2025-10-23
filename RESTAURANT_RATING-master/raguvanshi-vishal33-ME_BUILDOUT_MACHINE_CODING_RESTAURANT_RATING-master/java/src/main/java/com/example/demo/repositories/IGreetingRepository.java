package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Greeting;

public interface IGreetingRepository {
    Greeting save(Greeting greeting);
    boolean existsById(Long id);
    Optional<Greeting> findById(Long id);
    List<Greeting> findAll();
    void deleteById(Long id);
    long count();
}
