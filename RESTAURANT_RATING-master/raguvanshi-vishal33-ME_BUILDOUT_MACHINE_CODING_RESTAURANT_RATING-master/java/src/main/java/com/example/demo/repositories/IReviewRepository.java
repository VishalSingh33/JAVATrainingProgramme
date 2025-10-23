package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;
import com.example.demo.entities.User;

public interface IReviewRepository {

    Review save(Review review, User user, Restaurant restaurant);

    List<Review> findAll();

    Optional<Review> findById(Long id);

    Review findByUserAndRestaurant(User user, Restaurant restaurant);

    boolean reviewExists(Review review);

    void delete(Review review);

    int count();

    void updateRevMapWithNewReview(Long id, Review review);
}
