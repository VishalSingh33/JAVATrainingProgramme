package com.geektrust.backend.controller;

import java.util.List;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.ICourseService;

public class AddCourseController implements IController{

    private final ICourseService courseService;
    
    public AddCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    
    // Sample Input Token List:- ["ADD-COURSE-OFFERING","JAVA","JAMES","15062022","1","2"]

    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() < 2) {
                throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
            }
            
            String courseName = tokens.get(1);
            String instructor = "";
            String date = "";
            int minCapacity = 0;
            int maxCapacity = 0;
            
            if (tokens.size() >= 4) {
                instructor = tokens.get(2);
                date = tokens.get(3);
                minCapacity = Integer.parseInt(tokens.get(4));
                maxCapacity = Integer.parseInt(tokens.get(5));
            }
    
            if (instructor == null || instructor.isEmpty() || date == null || date.isEmpty()) {
                throw new InvalidInputException("INPUT_DATA_ERROR\n(because of missing instructor and course-offering-date)\n");
            }
    
            Course course = courseService.create(courseName, instructor, date, minCapacity, maxCapacity);
            String courseID = "OFFERING-" + course.getCourseName() + "-" + course.getInstructor();
            System.out.println(courseID);
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    
}