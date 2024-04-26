package com.notification.service.exceptionHandler;

public class RestaurantNotFountException extends RuntimeException {
    public RestaurantNotFountException(String message) {
        super(message);
    }
}