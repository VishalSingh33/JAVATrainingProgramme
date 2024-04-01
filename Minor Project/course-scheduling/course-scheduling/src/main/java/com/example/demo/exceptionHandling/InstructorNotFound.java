package com.example.demo.exceptionHandling;

public class InstructorNotFound extends RuntimeException{
    public InstructorNotFound()
    {
     super();
    }
    public InstructorNotFound(String msg)
    {
     super(msg);
    }
}