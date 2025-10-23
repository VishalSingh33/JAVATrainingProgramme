package com.geektrust.backend.controller;

import java.util.List;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.ICourseService;

public class AddCourseController implements ICommand {

    private final ICourseService courseService;
  

    public AddCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }
    // Sample Input Token List:- ["ADD-COURSE-OFFERING","JAVA","JAMES","15062022","1","2"]

    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() < IndexCommand.INSTRUCTOR_INDEX.getIndex()) {
                throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
            }

            String courseName = tokens.get(IndexCommand.COURSE_NAME_INDEX.getIndex());
            String instructor = "";
            String date = "";
            int minCapacity = 0;
            int maxCapacity = 0;

            if (tokens.size() >= IndexCommand.MIN_CAPACITY_INDEX.getIndex() ) {
                instructor = tokens.get(IndexCommand.INSTRUCTOR_INDEX.getIndex());
                date = tokens.get(IndexCommand.DATE_INDEX.getIndex());
                minCapacity = Integer.parseInt(tokens.get(IndexCommand.MIN_CAPACITY_INDEX.getIndex()));
                maxCapacity = Integer.parseInt(tokens.get(IndexCommand.MAX_CAPACITY_INDEX.getIndex()));
            }

            if (instructor == null || instructor.isEmpty() || date == null || date.isEmpty()) {
                throw new InvalidInputException(
                        "INPUT_DATA_ERROR\n(because of missing instructor and course-offering-date)\n");
            }

            Course course =
                    courseService.create(courseName, instructor, date, minCapacity, maxCapacity);
            String courseID = "OFFERING-" + course.getCourseName() + "-" + course.getInstructor();
            System.out.println(courseID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
