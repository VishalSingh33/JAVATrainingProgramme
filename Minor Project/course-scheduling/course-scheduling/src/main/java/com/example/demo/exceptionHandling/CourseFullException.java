package com.example.demo.exceptionHandling;

public class CourseFullException extends RuntimeException{
    public CourseFullException()
    {
     super();
    }
    public CourseFullException(String msg)
    {
     super(msg);
    }
}