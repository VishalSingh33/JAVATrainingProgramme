package com.geektrust.backend.exceptions;

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