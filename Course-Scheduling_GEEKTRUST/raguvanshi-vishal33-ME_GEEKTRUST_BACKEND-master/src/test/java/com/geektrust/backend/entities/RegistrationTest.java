package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RegistrationTest")
public class RegistrationTest {
    

@Test
@DisplayName("Course Registration Test For An Employee")
public void courseRegistrationTest(){
    //Arrange
    
    String expectedOutput = "REG-COURSE-ANDY-JAVA"; 

   
    String courseName="JAVA";
    String instructor="JAMES";
    String date="15062022";
    int minCapacity=1;
    int maxCapacity=2;
    boolean isAllotted=false;
    boolean isCancelled=false;
    String courseID="OFFERING-"+courseName+"-"+instructor;

    Course course = new Course(courseID, courseName, instructor, date, minCapacity, maxCapacity, isAllotted, isCancelled);
    
    String regId="REG-COURSE-ANDY-JAVA";
    String emailAddress="ANDY@GMAIL.COM";   
    boolean isAccepted=true;
   
    Registration reg= new Registration(regId, emailAddress, course.getCourseID() , isAccepted);
   
    //Assert
    Assertions.assertEquals(reg.getRegId(), expectedOutput);
  
}
}