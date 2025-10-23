package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CourseTest")
public class CourseTest {
    

@Test
@DisplayName("Add Course Test")
public void addCourseTest(){
    //Arrange
    
    String expectedOutput = "OFFERING-JAVA-JAMES";        
    String courseName="JAVA";
    String instructor="JAMES";
    String date="15062022";
    int minCapacity=1;
    int maxCapacity=2;
    boolean isAllotted=false;
    boolean isCancelled=false;
    String courseID="OFFERING-"+courseName+"-"+instructor;

    Course course = new Course(courseID, courseName, instructor, date, minCapacity, maxCapacity, isAllotted, isCancelled);
    
    //Assert
    Assertions.assertEquals(course.getCourseID(), expectedOutput);
   
}
}