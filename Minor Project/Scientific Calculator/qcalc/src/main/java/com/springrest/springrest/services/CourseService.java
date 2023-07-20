package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import com.springrest.springrest.entities.Course;

public interface CourseService 
{
	public List<Course> getCourse();
  
  public Course findCoursebyId(Long courseId);

  public Course addCourse(Course course);

  public Course updateCourse( Long id,Course course);

  public void deleteUser(Long courseId);

	

//public void deleteCourse(long parseLong);


}
