package com.example.demo.ExceptionHandling;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException()
    {
     super();
    }
    public InvalidOperationException(String msg)
    {
     super(msg);
    }
}
