package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.Dao.CourseDao;
import com.springrest.springrest.entities.Course;


@Service
public class CourseServiceImpl implements CourseService
{
    @Autowired
	private CourseDao courseDao;
    
	//List<Course> list;
//	CourseServiceImpl()
//	{
//		list=new ArrayList<>();
//		
//		list.add(new Course(111,"java", "java tutorial"));
//		list.add(new Course(112,"spring", "spring tutorial"));
//		
//	}
	
	
	
	@Override
	public List<Course> getCourse() 
	{
		return courseDao.findAll();
	}

@Override
	public Course findCoursebyId(Long courseId) {
		Optional<Course> course = courseDao.findById(courseId);
    if (course.isPresent())
		{
			return course.get();
		}
		throw new RuntimeException("Course search is not found" + courseId);
	}
	

	@Override
	public Course addCourse(Course course) {
		//list.add(course);
		courseDao.save(course);
		return course;
	}


	@Override
	public Course updateCourse(Long id,Course course)
	{
		Optional<Course> course1=this.courseDao.findById(id);
		if(course1.isPresent())
		{
			Course cour=course1.get();
			cour.setId(course.getId());
			cour.setTitle(course.getTitle());
			cour.setDescription(course.getDescription());
			return this.courseDao.save(cour);
		}
		else
		{
			return null;
		}
		
	}


	@Override
	public void deleteUser(Long courseId) {
		this.courseDao.deleteById(courseId);
	}

}
