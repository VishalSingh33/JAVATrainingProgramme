package com.geektrust.backend.exceptions;

public class TrainNotFoundException extends RuntimeException {
    public TrainNotFoundException()
    {
     super();
    }
    public TrainNotFoundException(String msg)
    {
     super(msg);
    }
}
