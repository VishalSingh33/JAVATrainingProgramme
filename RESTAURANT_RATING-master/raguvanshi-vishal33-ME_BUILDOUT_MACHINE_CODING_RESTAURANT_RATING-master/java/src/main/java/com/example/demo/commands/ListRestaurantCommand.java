package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.RestaurantServices;

public class ListRestaurantCommand implements ICommand {
    private final RestaurantServices restaurantServices;

    public ListRestaurantCommand(RestaurantServices restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        restaurantServices.listRestaurant();
            // System.out.println("Song [id=" + song.getId() + "]");

    }
}
