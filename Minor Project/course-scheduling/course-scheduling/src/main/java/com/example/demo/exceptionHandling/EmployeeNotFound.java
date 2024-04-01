package com.example.demo.exceptionHandling;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound()
    {
     super();
    }
    public EmployeeNotFound(String msg)
    {
     super(msg);
    }
}