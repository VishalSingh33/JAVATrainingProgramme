package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.RestaurantServices;

public class DescribeRestaurantCommand implements ICommand {
    private final RestaurantServices restaurantServices;

    public DescribeRestaurantCommand(RestaurantServices restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    @Override
    public void invoke(List<String> tokens) {

        String restaurant = tokens.get(1);

        if (restaurant.isEmpty()) {
            throw new RuntimeException("Fields are Empty");

        } else {
            Long restaurantID = Long.valueOf(tokens.get(1));
            restaurantServices.describeRestaurant(restaurantID);
            // System.out.println("Song [id=" + song.getId() + "]");

        }

    }
}
