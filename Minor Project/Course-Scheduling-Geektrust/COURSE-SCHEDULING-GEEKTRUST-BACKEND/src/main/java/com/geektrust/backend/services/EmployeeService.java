package com.geektrust.backend.services;

import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.utility.EmailValidator;

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
