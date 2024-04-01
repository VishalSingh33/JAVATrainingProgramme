package com.example.demo.exceptionHandling;

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