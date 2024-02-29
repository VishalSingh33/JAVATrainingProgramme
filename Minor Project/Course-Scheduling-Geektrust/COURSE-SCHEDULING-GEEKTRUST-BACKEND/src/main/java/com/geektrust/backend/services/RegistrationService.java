package com.geektrust.backend.services;

import com.geektrust.backend.entities.Course;
import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.entities.Registration;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;
import com.geektrust.backend.utility.EmailValidator;

public class RegistrationService implements IRegistrationService{
    private final ICourseRepository courseRepository;
    private final IEmployeeRepository employeeRepository;
    private final IRegistrationRepository registrationRepository;

    

public RegistrationService(ICourseRepository courseRepository, IEmployeeRepository employeeRepository, IRegistrationRepository registrationRepository) {
this.courseRepository = courseRepository;
this.employeeRepository = employeeRepository;
this.registrationRepository=registrationRepository;
}

@Override
public String create(String emailId, String courseOfferingId) {
if(EmailValidator.isValidEmailAddress(emailId)) {
 // SAVE EMPLOYEE DATA FIRST
 employeeRepository.save(new Employee(emailId));
}
else{
    emailId=null;
}

if (emailId == null || emailId.isEmpty() || courseOfferingId == null || courseOfferingId.isEmpty()) {
    //throw new InvalidInputException("INPUT_DATA_ERROR\n(because email and  course-offering-id missing)\n");
    throw new InvalidInputException("INPUT_DATA_ERROR");

}

    // FETCH COURSE DATA
    Course course =courseRepository.findCourseInfo(courseOfferingId); 
    String courseName=course.getCourseName();   
    int maxCapacity=course.getMaxCapacity();
    long enrolledNumTemp=registrationRepository.count();     
    int enrolledNum=(int)enrolledNumTemp;
    boolean isAllotted=course.isAllotted();
    boolean isCancelled= course.isCancelled();

    // FETCH EMPLOYEE DATA
    Employee emp=employeeRepository.findNameByEmail(emailId);
    String empName=emp.getName();

    boolean isAccepted=false;
    String regId="REG-COURSE-"+empName+"-"+courseName;
   
    if(courseName!=""){
       
        if(!isAllotted || isCancelled){
            if(enrolledNum==maxCapacity){   
               // return "COURSE_FULL_ERROR\n(because max limit of "+courseName+" course="+maxCapacity+")";
                return "COURSE_FULL_ERROR";
               
            }else{
                //register the employee to course......
             isAccepted=true;
             registrationRepository.save(new Registration(regId, emailId, courseOfferingId, isAccepted));
             regId=regId+" ACCEPTED";  
             return regId;
            }
        }else{
            //In case of course is allotted already
           return null;
        }
    }else{
        //In case of course ID is not present in the course offering list.
       
        return "INPUT_DATA_ERROR";
    }
       
}

}
