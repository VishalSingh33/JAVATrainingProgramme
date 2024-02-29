package com.geektrust.backend.repositories;

import java.util.List;
import com.geektrust.backend.entities.Course;

public interface ICourseRepository extends CRUDRepository<Course,String> { 
    public Course findCourseInfo(String courseOfferingId);   
    public List<Course> findAllotedCourse(Boolean isAlloted);
    public List<Course> findCancelCourse(Boolean isCancel);
   
}
