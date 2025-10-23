package com.geektrust.backend.services;

import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.entities.CourseStatus;
import com.geektrust.backend.entities.Registration;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;

public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final IRegistrationRepository registrationRepository;



    public CourseService(ICourseRepository courseRepository, IRegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;

    }


    @Override
    public Course create(String courseName, String instructor, String date, int minCapacity,
            int maxCapacity) throws InvalidInputException {
        registrationRepository.deleteAll();
        if (courseName == null || courseName.isEmpty() || instructor == null || instructor.isEmpty()
                || date == null || date.isEmpty() || minCapacity <= 0 || maxCapacity <= 0
                || minCapacity > maxCapacity) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        String courseId = "OFFERING-" + courseName + "-" + instructor;
        return courseRepository.save(new Course(courseId, courseName, instructor, date, minCapacity,
                maxCapacity, false, false));

    }



    private String processCourseStatus(Course course, List<Registration> regEmployees, CourseStatus status) {
        String output = "";
        for (Registration e : regEmployees) {
            output += e.getRegId() + " " + e.getEmailAddress() + " " + course.getCourseID()
                    + " " + course.getCourseName() + " " + course.getInstructor() + " "
                    + course.getDate() + " " + status + "\n";
        }
        return output;
    }

    @Override
    public String allotCourse(String courseOfferingId) {
        Course course = courseRepository.findCourseInfo(courseOfferingId);
        long enrolledNumTemp = registrationRepository.count();
        int enrolledNum = (int) enrolledNumTemp;

        List<Registration> regEmployees = registrationRepository.findAll();

        String output = "";

        if (enrolledNum < course.getMinCapacity()) {
            course.setCancelled(true);
            CourseStatus status = course.isCancelled() ? CourseStatus.COURSE_CANCELED : CourseStatus.CONFIRMED;
            return processCourseStatus(course, regEmployees, status);
        } else {
            course.setAllotted(true);
            CourseStatus status = course.isCancelled() ? CourseStatus.COURSE_CANCELED : CourseStatus.CONFIRMED;
            return processCourseStatus(course, regEmployees, status);
        }
    }

    @Override
    public String cancelCourse(String courseRegistrationId) {
        List<Registration> regEmployees = registrationRepository.findAll().stream()
                .filter(e -> e.getRegId().equals(courseRegistrationId))
                .collect(Collectors.toList());
        String courseOfferingId = regEmployees.get(0).getCourseOfferingId();
        Course course = courseRepository.findCourseInfo(courseOfferingId);
        String output = "";
        if (course.isAllotted()) {
            CourseStatus status = CourseStatus.CANCEL_REJECTED;
            output += regEmployees.get(0).getRegId() + " " + status + "\n"
                    + "(because ALLOT course-offering is already run)";
            return output;
        } else {
            CourseStatus status = CourseStatus.CANCEL_ACCEPTED;
            output += regEmployees.get(0).getRegId() + " " + status;

            // Remove the registration for the employee...
            String emailId = regEmployees.get(0).getEmailAddress();
            registrationRepository.deleteById(emailId);
            return output;
        }
    }


}
