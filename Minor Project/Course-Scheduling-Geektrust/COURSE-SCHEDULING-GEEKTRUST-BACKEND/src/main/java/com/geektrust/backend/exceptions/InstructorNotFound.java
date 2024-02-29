package com.geektrust.backend.exceptions;

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