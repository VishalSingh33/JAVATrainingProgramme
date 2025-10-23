package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.OrderSort;
import com.example.demo.services.RestaurantServices;

public class GetReviewsFilterOrderCommand implements ICommand {
    private final RestaurantServices restaurantServices;

    public GetReviewsFilterOrderCommand(RestaurantServices restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    @Override
    public void invoke(List<String> tokens) {

        Long restaurantID = Long.valueOf(tokens.get(1));
        int min = Integer.parseInt(tokens.get(2));
        int max = Integer.parseInt(tokens.get(3));
        OrderSort order = OrderSort.valueOf(tokens.get(4));

        restaurantServices.getReviewsFilterOrderByRating(restaurantID, min, max, order);
        // System.out.println("Song [id=" + song.getId() + "]");

    }
}
