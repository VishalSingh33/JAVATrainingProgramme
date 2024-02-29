package com.geektrust.backend.services;

import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.exceptions.InvalidInputException;

public interface IEmployeeService {
    public Employee create(String emailAddress) throws InvalidInputException;   
   
}
   
