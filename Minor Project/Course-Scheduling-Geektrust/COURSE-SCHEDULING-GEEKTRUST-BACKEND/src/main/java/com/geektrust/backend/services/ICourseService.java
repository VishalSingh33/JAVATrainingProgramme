package com.geektrust.backend.services;

import com.geektrust.backend.entities.Course;
import com.geektrust.backend.exceptions.InvalidInputException;

public interface ICourseService {
    public Course create(String courseName, String instructor, String date, int minCapacity, int maxCapacity) throws InvalidInputException;  
    public String allotCourse(String courseOfferingId);
    public String cancelCourse(String courseRegistrationId);

       
}



