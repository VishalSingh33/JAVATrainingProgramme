package com.geektrust.backend.controller;

import java.util.List;
import com.geektrust.backend.services.ICourseService;

public class CancelCourseController implements ICommand {

    private final ICourseService courseService;

    public CancelCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }
    @Override
    public void execute(List<String> tokens) {

        try {
            if (tokens.size() == IndexCommand.TWO_INDEX.getIndex()) {
                String courseOfferingId = tokens.get(IndexCommand.ONE_INDEX.getIndex());
                String result = "";
                result = courseService.cancelCourse(courseOfferingId);
                System.out.println(result);
            } else {
                System.out.println("INPUT_DATA_ERROR");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
