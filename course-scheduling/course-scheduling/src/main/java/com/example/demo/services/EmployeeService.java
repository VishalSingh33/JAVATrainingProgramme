package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.entities.Employee;
import com.example.demo.exceptionHandling.InvalidInputException;
import com.example.demo.repositories.IEmployeeRepository;
import com.example.demo.utility.EmailValidator;

public class EmployeeService implements IEmployeeService {

    IEmployeeRepository iEmployeeRepository;


    public EmployeeService(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }


    @Override
    public Employee create(String email) {
        if(EmailValidator.isValidEmailAddress(email)) {
            // SAVE EMPLOYEE DATA FIRST
            return iEmployeeRepository.save(new Employee(email));
           }
           else{
            //email=null;
            throw new InvalidInputException("INPUT_DATA_ERROR\n(Invalid Email Id)\n");

           }
      
      
    }
}