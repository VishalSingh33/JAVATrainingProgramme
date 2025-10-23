package com.example.demo.repositories;

import com.example.demo.entities.Employee;

public interface IEmployeeRepository extends CRUDRepository<Employee,String> {
   
    public Employee findNameByEmail(String emailAddress); 
    
}