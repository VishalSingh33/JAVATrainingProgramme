package com.springrest.springrest.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entities.Course;
import java.util.List;
// import java.util.Set;


@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
	// @Query(nativeQuery = true, value = "select * from zmployees where id=1;")
	// List<Course> findCoursebyId(Long courseId);
}
