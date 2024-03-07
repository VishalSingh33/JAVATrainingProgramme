package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.entities.OrderSort;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;
import com.example.demo.repositories.IRestaurantRepositories;

public class RestaurantServices {
    private final IRestaurantRepositories restaurantRepo;

    public RestaurantServices(IRestaurantRepositories restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    //@Override
    public void registerRestaurant(String restaurantName) {
        Restaurant restaurant = restaurantRepo.save(new Restaurant(restaurantName));
        System.out.println("Restaurant "+ "["+"id="+restaurant.getId()+"]");

    }
    
    Comparator<Review> comparator = (r1, r2) -> {
        int rating1 = r1.getRating();
        int rating2 = r2.getRating();
        return Integer.compare(rating1, rating2);
    };
    Comparator<Review> reverseComparator = (r1, r2) -> {
        int rating1 = r1.getRating();
        int rating2 = r2.getRating();
        return Integer.compare(rating2, rating1); // Reversing the order of comparison
    };

    // @Override
    public void getReviewsOrderByRating(Long restaurantID, OrderSort order) {
        
        Restaurant restaurant = restaurantRepo.findById(restaurantID).orElseThrow(() -> new RuntimeException(
                        "Restaurant with restaurant id: " + restaurantID + " not found!"));
        List<Review> reviewList = restaurant.getreviewsList();

        // if (OrderSort.RATING_ASC == order)
        // reviewList =
        // reviewList.stream().sorted(Comparator.comparingInt(Review::getRating)).toList();
        // else
        // reviewList =
        // reviewList.stream().sorted(Comparator.comparingInt(Review::getRating).reversed()).toList();

        if (OrderSort.RATING_ASC == order) {
            reviewList.sort(comparator);  //If sorting order is ascending
        } else {
            reviewList.sort(reverseComparator); //If sorting order is descending
        }

        System.out.print('[');
        for (int i = 0; i < reviewList.size(); i++) {
            System.out.print(reviewList.get(i));
            if (i < reviewList.size() - 1)
                System.out.print(", ");
        }
        System.out.println(']');
    }

    //@Override
    public void getReviewsFilterOrderByRating(Long restaurantID, int min, int max, OrderSort order) {
        
        Restaurant restaurant = restaurantRepo.findById(restaurantID).orElseThrow(() 
            -> new RuntimeException("Restaurant with restaurant id: " + restaurantID + " not found!"));

        List<Review> reviewList = restaurant.getreviewsList();
        // if (OrderSort.RATING_ASC == order)
        // reviewList = 
        // reviewList.stream().filter(review -> review.getRating() >= min && review.getRating() <= max)
        // .sorted(Comparator.comparingInt(Review::getRating)).collect(Collectors.toList());
        // else
        // reviewList = 
        // reviewList.stream().filter(review -> review.getRating() >= min && review.getRating() <= max)
        // .sorted(Comparator.comparingInt(Review::getRating).reversed()).collect(Collectors.toList());
        
        List<Review> filteredList = new ArrayList<>();
        // Filter reviews based on rating range
        for (Review review : reviewList) {
            int rating = review.getRating();
            if (rating >= min && rating <= max) {
                filteredList.add(review);
            }
        }
        // Sort filtered list based on order
        if (OrderSort.RATING_ASC == order) {
            filteredList.sort(comparator);  //If sorting order is ascending
        } else {
            filteredList.sort(reverseComparator); //If sorting order is descending
        }
        System.out.print('[');
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.print(filteredList.get(i));
            if (i < filteredList.size() - 1)
                System.out.print(", ");
        }
        System.out.println(']');
    }

    //@Override
    public void describeRestaurant(Long restaurantID) {
        Restaurant restaurant = restaurantRepo.findById(restaurantID).orElseThrow(() 
        -> new RuntimeException("Restaurant with restaurant id: " + restaurantID + " not found!"));
        System.out.println(restaurant.descRestaurant());
    }

    //@Override
    public void listRestaurant() {
        List<Restaurant> restaurantList = restaurantRepo.findAll();

        System.out.print('[');
        for (int i = 0; i < restaurantList.size(); i++) {
            System.out.print("Restaurant [id=" + restaurantList.get(i).getId() + "]");
            if (i < restaurantList.size() - 1)
                System.out.print(", ");
        }
        System.out.println(']');

    }

}
