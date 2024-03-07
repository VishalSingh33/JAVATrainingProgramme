package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EmployeeTest")
public class EmployeeTest {
    

@Test
@DisplayName("Add Employee and Get Name From EmailId Test")
public void getName_FromEmployeeEmail_Test(){
    //Arrange
    
    String expectedOutput = "ANDY";        
    String email="ANDY@GMAIL.COM";
  
   Employee emp=new Employee(email);
    
    //Assert
    Assertions.assertEquals(emp.getName(), expectedOutput);
  
}
}