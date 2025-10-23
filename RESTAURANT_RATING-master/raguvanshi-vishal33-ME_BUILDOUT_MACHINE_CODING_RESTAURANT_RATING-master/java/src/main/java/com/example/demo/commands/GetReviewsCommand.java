package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.OrderSort;
import com.example.demo.services.RestaurantServices;

public class GetReviewsCommand implements ICommand {
    private final RestaurantServices restaurantServices;

    public GetReviewsCommand(RestaurantServices restaurantServices) {
        this.restaurantServices = restaurantServices;
    }

    @Override
    public void invoke(List<String> tokens) {

        // String rating = tokens.get(1);
        // String order = tokens.get(2);
        Long restaurantID = Long.valueOf(tokens.get(1));
        OrderSort order = OrderSort.valueOf(tokens.get(2));

        if (restaurantID == null || restaurantID == null) {
            throw new RuntimeException("Fields are Empty");
        } else {
            restaurantServices.getReviewsOrderByRating(restaurantID, order);
            // System.out.println("Song [id=" + song.getId() + "]");
        }

    }
}
