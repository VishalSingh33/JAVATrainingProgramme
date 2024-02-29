package com.geektrust.backend.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException()
    {
     super();
    }
    public InvalidInputException(String msg)
    {
     super(msg);
    }
}