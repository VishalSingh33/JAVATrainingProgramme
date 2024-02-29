package com.example.demo.repositories;

import java.util.*;
import com.example.demo.entities.Restaurant;

public class RestaurantRepository implements IRestaurantRepositories {
    
    private final Map<Long, Restaurant> restaurantMap;
    private Long autoIncreament = 1L;
    public RestaurantRepository() {
        this.restaurantMap = new HashMap<>();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Restaurant restaurants = new Restaurant(autoIncreament, restaurant.getRestaurantName());
        restaurantMap.put(autoIncreament++, restaurants);
        return restaurants;
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantMap.values());
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    @Override
    public Optional<Restaurant> findByName(String restaurantName) {
        for (Map.Entry<Long, Restaurant> entry : restaurantMap.entrySet()) {
            if (entry.getValue().getRestaurantName().equalsIgnoreCase(restaurantName))
                return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty();
    }

    @Override
    public boolean restaurantExists(Restaurant restaurant) {
        // return restaurantMap.values().stream().anyMatch(value -> value.equals(restaurant));
        return restaurantMap.containsKey(restaurant.getId());
    }

    @Override
    public void delete(Restaurant restaurant) {
        restaurantMap.remove(restaurant.getId());
    }

    @Override
    public int count() {
        return restaurantMap.size();
    }
    
}
