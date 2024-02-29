package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.CourseRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;
import com.geektrust.backend.repositories.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseServiceTest {

    private CourseService courseService;

    @BeforeEach
    void setUp() throws Exception {
        ICourseRepository courseRepository = new CourseRepository();
        IEmployeeRepository employeeRepository = new EmployeeRepository();
        IRegistrationRepository registrationRepository = new RegistrationRepository();

        courseService = new CourseService(courseRepository, employeeRepository, registrationRepository);
    }

    @Test
    void testCreateWithValidInput() {
        try {

            String courseName="JAVA";
            String instructor="JAMES";
            String date="15062022";
            int minCapacity=1;
            int maxCapacity=2;
            boolean isAllotted=false;
            boolean isCancelled=false;
            String courseID="OFFERING-"+courseName+"-"+instructor;

            Course course = courseService.create(courseName, instructor, date, minCapacity, maxCapacity);
            assertEquals(courseID, course.getCourseID());
            assertEquals(courseName, course.getCourseName());
            assertEquals(instructor, course.getInstructor());
            assertEquals(date, course.getDate());
            assertEquals(minCapacity, course.getMinCapacity());
            assertEquals(maxCapacity, course.getMaxCapacity());
            assertFalse(course.isCancelled());
            assertFalse(course.isAllotted());
        } catch (InvalidInputException e) {
            fail("Unexpected InvalidInputException thrown");
        }
    }

    @Test
    void testCreateWithNullCourseName() {
        assertThrows(InvalidInputException.class, () -> {
            courseService.create(null, "JAMES", "15062022", 5, 10);
        });
    }

    @Test
    void testCreateWithEmptyCourseName() {
        assertThrows(InvalidInputException.class, () -> {
            courseService.create("", "JAMES", "15062022", 5, 10);
        });
    }

    @Test
    void testCreateWithNullInstructor() {
        assertThrows(InvalidInputException.class, () -> {
            courseService.create("JAVA", null, "15062022", 5, 10);
        });
    }

    @Test
    void testCreateWithEmptyInstructor() {
        assertThrows(InvalidInputException.class, () -> {
            courseService.create("JAVA", "", "15062022", 5, 10);
        });
    }



  }
