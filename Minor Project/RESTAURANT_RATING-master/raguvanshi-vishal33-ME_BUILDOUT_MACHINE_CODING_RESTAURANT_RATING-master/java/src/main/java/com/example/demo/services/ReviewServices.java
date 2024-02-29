package com.example.demo.services;

import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;
import com.example.demo.repositories.IRestaurantRepositories;
import com.example.demo.repositories.IReviewRepository;
import com.example.demo.repositories.IUserRepositories;

public class ReviewServices {
    private final IReviewRepository reviewRepo;
    private final IUserRepositories userRepo;
    private final IRestaurantRepositories restaurantRepo;

    public ReviewServices(IReviewRepository reviewRepo, IUserRepositories userRepo, IRestaurantRepositories restaurantRepo) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.restaurantRepo = restaurantRepo;
    }

    private Review getReviewIfPresentAlready(User user, Restaurant restaurant) {
        return reviewRepo.findByUserAndRestaurant(user, restaurant);
    }
    //@Override
    public void addRating(int rating, Long userId, Long restaurantId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User with userID: " + userId + " not found"));
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant with restaurantID: " + userId + " not found"));
        Review review = getReviewIfPresentAlready(user, restaurant);
        if (review != null) // this review is already added from the user to the restaurant but may be with different rating
        {
            Review newReview = new Review(review.getId(), rating, user, restaurant); // created a review with new rating to override the old one
            reviewRepo.updateRevMapWithNewReview(review.getId(), newReview);

            user.setReviewsList(user.getIndexOfInReviewList(review), newReview);
            user.refreshUserLevel();

            restaurant.setReviewsList(restaurant.getIndexOfInReviewList(review), newReview);
            restaurant.refreshAvgRating();

            review = newReview;
        } else {
            review = reviewRepo.save(new Review(rating), user, restaurant);
            user.addToReviewList(review);
            user.refreshUserLevel();

            restaurant.addToReviewList(review);
            restaurant.refreshAvgRating();
        }
        System.out.println(review + " added successfully for " + "Restaurant "+ "["+"id="+restaurant.getId()+"]" + " by " + user + "!");
    }
    //@Override
    public void addReview(int rating, Long userId, Long restaurantId, String dishNames, String descriptions) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User with userID: " + userId + " not found"));
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant with restaurantID: " + userId + " not found"));
        Review review = getReviewIfPresentAlready(user, restaurant);
        if (review != null) // this review is already added from the user to the restaurant but may be with different rating
        {
            Review newReview = new Review(review.getId(), rating, dishNames, descriptions, user, restaurant); // created a review with new rating to override the old one
            reviewRepo.updateRevMapWithNewReview(review.getId(), newReview);

            user.setReviewsList(user.getIndexOfInReviewList(review), newReview);
            user.refreshUserLevel();

            restaurant.setReviewsList(restaurant.getIndexOfInReviewList(review), newReview);
            restaurant.refreshAvgRating();

            review = newReview;
        } else {
            review = reviewRepo.save(new Review(rating, dishNames, descriptions), user, restaurant);
            user.addToReviewList(review);
            user.refreshUserLevel();

            restaurant.addToReviewList(review);
            restaurant.refreshAvgRating();
        }
        System.out.println(review + " added successfully for " + "Restaurant "+ "["+"id="+restaurant.getId()+"]" + " by " + user);
    }


     // public void addRating(String userName, String restaurantName, int rating, ArrayList<String> dishes, ArrayList<String> comment) {
    //     if(!isRestaurantExist(restaurantName)) {
    //         throw new RestaurantNotFountException(restaurantName + " Not Found.");
    //     }
    //     Restaurant restaurant = getRestaurant(restaurantName);
    //     Review review = getReviewObject(rating, userName, dishes, comment);
    //     addRatingForRestaurant(restaurant, review, userName);
    // }

    // private void addRatingForRestaurant(Restaurant restaurant, Review review, String userName) {
    //     ArrayList<Integer> indexAndRating = getUserReviewIndexAndRating(userName, restaurant);
    //     int totalRating = restaurant.getTotalRating();
    //     int totalRaters = restaurant.getTotalRaters();
    //     if(indexAndRating.size() != 0) {
    //         restaurant.setTotalRating(totalRating - indexAndRating.get(1));
    //         restaurant.setTotalRating(totalRaters - 1);
    //         restaurant.getRestaurantReviewList().remove(indexAndRating.get(0).intValue());
    //         totalRating -= indexAndRating.get(1);
    //         totalRaters -= 1;
    //     }
    //     restaurant.getRestaurantReviewList().add(review);
    //     restaurant.setTotalRating(totalRating + review.getRating());
    //     restaurant.setTotalRaters(totalRaters + 1);
    // }

}
