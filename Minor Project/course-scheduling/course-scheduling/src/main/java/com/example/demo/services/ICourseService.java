package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.exceptionHandling.InvalidInputException;

public interface ICourseService {
    
    public Course create(String courseName, String instructor, String date, int minCapacity, int maxCapacity) throws InvalidInputException;  
    public String allotCourse(String courseOfferingId);
    public String cancelCourse(String courseRegistrationId);

       
}

