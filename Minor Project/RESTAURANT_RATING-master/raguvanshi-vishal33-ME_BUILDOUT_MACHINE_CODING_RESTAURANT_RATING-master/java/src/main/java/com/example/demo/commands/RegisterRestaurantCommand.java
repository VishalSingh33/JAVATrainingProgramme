package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.RestaurantServices;

public class RegisterRestaurantCommand implements ICommand {
    private final RestaurantServices restaurantServices;

    public RegisterRestaurantCommand(RestaurantServices restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        String restaurantName = tokens.get(1);
        restaurantServices.registerRestaurant(restaurantName);
            // System.out.println("Song [id=" + song.getId() + "]");

    }
}
