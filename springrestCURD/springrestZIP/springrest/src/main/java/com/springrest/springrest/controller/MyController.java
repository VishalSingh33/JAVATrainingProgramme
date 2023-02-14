package com.springrest.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

@Component
@RestController
public class MyController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/home")
	public String home() {
		return "welcome to courses application";
	}

	// get the courses
	@GetMapping("/courses")
	public List<Course> getCourses() {
		return this.courseService.getCourse();
	}

	// get single course by id
	@GetMapping("/courses/{courseId}")
	public Course findCoursebyId(@PathVariable String courseId)
	{
	return this.courseService.findCoursebyId(Long.parseLong(courseId));
	}


	// add Course
	@PostMapping("/courses")
	public Course Addcourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);

	}

	// update course by id
	@PutMapping("/courses/{id}")
	public Course updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		return this.courseService.updateCourse(id, course);
	}

	// delete course by id
	@DeleteMapping("/courses/{courseId}")
	public void deleteUser(@PathVariable("courseId") Long courseId) {
		this.courseService.deleteUser(courseId);
	}

}
