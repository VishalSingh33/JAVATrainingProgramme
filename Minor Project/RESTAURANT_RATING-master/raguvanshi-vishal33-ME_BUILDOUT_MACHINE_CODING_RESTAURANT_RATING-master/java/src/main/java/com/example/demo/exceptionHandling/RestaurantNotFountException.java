package com.example.demo.exceptionHandling;

public class RestaurantNotFountException extends RuntimeException {
    public RestaurantNotFountException(String message) {
        super(message);
    }
}