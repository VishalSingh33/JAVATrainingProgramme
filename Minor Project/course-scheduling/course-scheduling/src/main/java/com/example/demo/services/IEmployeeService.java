package com.example.demo.services;

import com.example.demo.entities.Employee;
import com.example.demo.exceptionHandling.InvalidInputException;

public interface IEmployeeService {
    public Employee create(String emailAddress) throws InvalidInputException;   
   
}
   
