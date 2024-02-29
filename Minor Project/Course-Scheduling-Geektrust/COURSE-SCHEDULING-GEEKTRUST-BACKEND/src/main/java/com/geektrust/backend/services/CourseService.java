package com.geektrust.backend.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.entities.Registration;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;

public class CourseService implements ICourseService {
    private Map<String,Course> registrationIdCourseMap;
    private final ICourseRepository courseRepository;
    private final IEmployeeRepository employeeRepository;
    private final IRegistrationRepository registrationRepository;
    

    
public CourseService(ICourseRepository courseRepository, IEmployeeRepository employeeRepository, IRegistrationRepository registrationRepository) {
this.courseRepository = courseRepository;
this.employeeRepository = employeeRepository;
this.registrationRepository = registrationRepository;

}


    @Override
    public Course create(String courseName, String instructor, String date, int minCapacity, int maxCapacity) throws InvalidInputException {
        if (courseName == null || courseName.isEmpty() || instructor == null || instructor.isEmpty() || date == null || date.isEmpty() || minCapacity <= 0 || maxCapacity <= 0 || minCapacity > maxCapacity) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        String courseId="OFFERING-"+courseName+"-"+instructor;       
        return courseRepository.save(new Course(courseId, courseName, instructor, date, minCapacity, maxCapacity, false, false));
            
    }



    @Override
    public String allotCourse(String courseOfferingId) {       
        Course course =courseRepository.findCourseInfo(courseOfferingId);
        long enrolledNumTemp=registrationRepository.count();     
        int enrolledNum=(int)enrolledNumTemp; 
       
        List<Registration> regEmployees=registrationRepository.findAll();      
        
        String output="";

        if(enrolledNum<course.getMinCapacity()){
            course.setCancelled(true);
            String status;
            if (course.isCancelled()) {
            status = "COURSE_CANCELED";
            } else {
            status = "CONFIRMED";
            }
            for (Registration e : regEmployees) {
                
                output+=e.getRegId()+" "+e.getEmailAddress()+" "+course.getCourseID()+" "+course.getCourseName()+" "+course.getInstructor()+" "
                       + course.getDate()+" "+status+"\n";               
             }           
            
            return output;

        }else{
            course.setAllotted(true);
            String status;
            if (course.isCancelled()) {
             status = "COURSE_CANCELED";
            } else {
            status = "CONFIRMED";
            }
            for (Registration e : regEmployees) {
                
                output+=e.getRegId()+" "+e.getEmailAddress()+" "+course.getCourseID()+" "+course.getCourseName()+" "+course.getInstructor()+" "
                       + course.getDate()+" "+status+"\n";               
             }           
            
            return output;

        }
        
    }


    @Override
    public String cancelCourse(String courseRegistrationId) {
        List<Registration> regEmployees=registrationRepository.findAll().stream()
        .filter(e-> e.getRegId().equals(courseRegistrationId))
        .collect(Collectors.toList());
        String courseOfferingId=regEmployees.get(0).getCourseOfferingId();
        Course course =courseRepository.findCourseInfo(courseOfferingId);
        String output="";
       {
            if(course.isAllotted()){
                String status ="CANCEL_REJECTED";
                output+=regEmployees.get(0).getRegId()+" "+status+"\n"+"(because ALLOT course-offering is already run)";  
                return output;
            }else{
               
                String status ="CANCEL_ACCEPTED";          
                output+=regEmployees.get(0).getRegId()+" "+status; 
                
                //Remove the registration for the employee... 
                String emailId=regEmployees.get(0).getEmailAddress();
                registrationRepository.deleteById(emailId);            
                return output;
            }
        }      

    }

    
}
