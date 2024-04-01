package com.example.demo.exceptionHandling;

public class NoSuchControllerException extends RuntimeException{
    public NoSuchControllerException()
    {
     super();
    }
    public NoSuchControllerException(String msg)
    {
     super(msg);
    }
}