package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.ReviewServices;

public class AddRatingCommand implements ICommand {
    private final ReviewServices reviewServices;

    public AddRatingCommand(ReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }
    @Override
    public void invoke(List<String> tokens) {

        String rating = tokens.get(1);
        Long userId = Long.valueOf(tokens.get(2));
        Long restaurantId = Long.valueOf(tokens.get(3));
        if(rating.isEmpty() ){
            throw new RuntimeException("Fields are Empty");
        } else {
             reviewServices.addRating(Integer.valueOf(rating), userId, restaurantId);
            // System.out.println("Song [id=" + song.getId() + "]");

        }

    }
}
