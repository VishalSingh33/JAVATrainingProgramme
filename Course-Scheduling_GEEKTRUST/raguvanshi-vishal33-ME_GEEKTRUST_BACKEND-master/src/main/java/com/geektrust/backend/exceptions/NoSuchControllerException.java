package com.geektrust.backend.exceptions;

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