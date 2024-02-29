package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final Long id;
    private String restaurantName;
    private List<Review> reviewsList;
    private float averageRating;
    // private int totalRating;
    // private int totalRaters;

    public Restaurant(Long id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.reviewsList = new ArrayList<>();
        this.averageRating = 0f;
    }

    public Restaurant(String restaurantName) { // constructor override with no ID
        this.id = null;
        this.restaurantName = restaurantName;
        this.reviewsList = new ArrayList<>();
        this.averageRating = 0f;
    }

    public Long getId() {
        return id;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public List<Review> getreviewsList() {
        return reviewsList;
    }
    public float getAverageRating() {
        return averageRating;
    }
    public void addRestaurantReview(Review review) {
        this.reviewsList.add(review);
    }

    public void refreshAvgRating() {
        int totalRating = 0;
        int totalReviews = reviewsList.size();

        for (Review review : reviewsList) {
            totalRating += review.getRating();
        }
        // averageRating = totalReviews > 0 ? (float) totalRating / totalReviews : 0.0f;
        if (totalReviews > 0) {
            averageRating = (float) totalRating / totalReviews;
        } else {
            averageRating = 0.0f;
        }

    }

    public void addToReviewList(Review review) {
        this.reviewsList.add(review);
    }

    public void setReviewsList(int index, Review review) {
        this.reviewsList.set(index, review);
    }

    public int getIndexOfInReviewList(Review review) {
        return this.reviewsList.indexOf(review);
    }

    public String descRestaurant() {
        return "Restaurant [" + "id=" + id + ", name=" + restaurantName + ", rating="
                + String.format("%.1f", averageRating) + ']';
    }
}
