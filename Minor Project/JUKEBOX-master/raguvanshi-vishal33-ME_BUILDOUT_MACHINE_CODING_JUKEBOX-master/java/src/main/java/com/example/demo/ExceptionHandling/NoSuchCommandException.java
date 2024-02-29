package com.example.demo.ExceptionHandling;

public class NoSuchCommandException extends RuntimeException{
    public NoSuchCommandException()
    {
     super();
    }
    public NoSuchCommandException(String msg)
    {
     super(msg);
    }
}
