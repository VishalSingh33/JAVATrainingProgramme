package com.example.demo.repositories;

import com.example.demo.entities.Restaurant;
import java.util.List;
import java.util.Optional;

public interface IRestaurantRepositories {
    
    Restaurant save(Restaurant restaurant);

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findByName(String restaurantName);

    boolean restaurantExists(Restaurant restaurant);

    void delete(Restaurant restaurant);

    int count();
}
