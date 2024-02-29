package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Course;

public class CourseRepository implements ICourseRepository {
   
    private final Map<String,Course> courseMap;

        
    public CourseRepository() {
        courseMap = new HashMap<String,Course>();
    }

    public CourseRepository(Map<String, Course> courseMap) {
        this.courseMap = courseMap;
       
    }

    @Override
    public Course save(Course entity) { 
        
        if(entity.getCourseID()!=null){           
            Course course = new Course(entity.getCourseID(), entity.getCourseName(),entity.getInstructor(), entity.getDate(), entity.getMinCapacity(), entity.getMaxCapacity(), false, false);
            courseMap.put(entity.getCourseID(),course);                    
            return course;       

        }
       
        courseMap.put(entity.getCourseID(),entity);
        return entity;           
       
    }
    
  
    @Override
    public List<Course> findAllotedCourse(Boolean isAlloted) {
        return courseMap.values().stream().filter(c->c.isAllotted()==true)
        .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCancelCourse(Boolean isCancel) {
        return courseMap.values().stream().filter(c->c.isCancelled()==true)
        .collect(Collectors.toList());
    }

    
    @Override
    public List<Course> findAll() {
        return courseMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courseMap.get(id));
    }

    
    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Course entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public Course findCourseInfo(String courseOfferingId) {
     return courseMap.values().stream().filter(s->s.getCourseID().equals(courseOfferingId)).findAny().get();
    }

    
}
