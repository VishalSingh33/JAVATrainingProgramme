package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Employee;

public interface IEmployeeRepository extends CRUDRepository<Employee,String> {
    public Employee findNameByEmail(String emailAddress); 
    
}