package com.geektrust.backend.services;

import com.geektrust.backend.entities.Course;
import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.entities.Registration;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;
import com.geektrust.backend.utility.EmailValidator;

public class RegistrationService implements IRegistrationService {
    private final ICourseRepository courseRepository;
    private final IEmployeeRepository employeeRepository;
    private final IRegistrationRepository registrationRepository;



    public RegistrationService(ICourseRepository courseRepository,
            IEmployeeRepository employeeRepository, IRegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public String create(String emailId, String courseOfferingId) {

        if (EmailValidator.isValidEmailAddress(emailId)) {
            // SAVE EMPLOYEE DATA FIRST
            employeeRepository.save(new Employee(emailId));
        } else {
            emailId = null;
        }
        if (emailId == null || emailId.isEmpty() || courseOfferingId == null || courseOfferingId.isEmpty()) {
            // throw new InvalidInputException("INPUT_DATA_ERROR\n(because email and
            // course-offering-id missing)\n");
            throw new InvalidInputException("INPUT_DATA_ERROR");

        }
        // FETCH COURSE DATA
        Course course = courseRepository.findCourseInfo(courseOfferingId);
        String courseName = course.getCourseName();
        int maxCapacity = course.getMaxCapacity();
        long enrolledNumTemp = registrationRepository.count();
        int enrolledNum = (int) enrolledNumTemp;
        boolean isAllotted = course.isAllotted();
        boolean isCancelled = course.isCancelled();

        // FETCH EMPLOYEE DATA
        Employee emp = employeeRepository.findNameByEmail(emailId);
        String empName = emp.getName();

        boolean isAccepted = false;
        String regId = "REG-COURSE-" + empName + "-" + courseName;

        if (courseName != "") {
            if (!isAllotted || isCancelled) {
                if (enrolledNum == maxCapacity) {
                    // return "COURSE_FULL_ERROR\n(because max limit of "+courseName+" course="+maxCapacity+")";
                    return "COURSE_FULL_ERROR";

                } else  {
                    // register the employee to course......
                    isAccepted = true;
                    registrationRepository.save(new Registration(regId, emailId, courseOfferingId, isAccepted));
                    regId = regId + " ACCEPTED";
                    return regId;
                }
            } else {
                // In case of course is allotted already
                return null;
            }
        } else {
            // In case of course ID is not present in the course offering list.
            return "INPUT_DATA_ERROR";
        }
    }

    // @Override
    // public String creates(RegistrationDto registrationDto) {
        
    //     if(!EmailValidator.isValidEmailAddress(registrationDto.getEmailAddress())){
    //         throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
    //     }
    //     validateCourse(registrationDto.getCourseID());
    //     if(seatsAvailability(registrationDto.getCourseID())){
    //         boolean b = employeeRepository.existsById(registrationDto.getEmailAddress());
    //         if(!b) employeeRepository.save(new EmployeeDto(registrationDto.getEmailAddress()));
    //         // if(!alreadyRegistered(registrationDto)){
    //         registrationDto.setRegID(getRegistrationId(registrationDto));
    //         registrationDto.setAccepted(true);
    //         String regId = registrationRepository.save(entity);
    //         return regId;
    //     }else{
    //         throw new InvalidInputException(Constant.COURSE_FULL_ERROR_MESSAGE);
    //     }
    // }
    
    // private String getRegistrationId(RegistrationDto registrationDto) {
    //     String courseName=getCourseName(registrationDto);
    //     String employeeName=getEmployeeName(registrationDto);
    //     return Constant.REGCOURSE+employeeName+Constant.HYPHEN+courseName;//REG-COURSE-JONE-JAVA
    // }

    // private String getNameFromCourseRepository(String courseId) {
    //     Course courseDto = courseRepository.findById(courseId).orElseThrow(() -> new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
    //     return courseDto.getCourseName();
    // }
    
    // private String getNameFromEmployeeRepository(String emailAddress) {
    //     Employee employeeDto = employeeRepository.findById(emailAddress).orElseThrow(() -> new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
    //     return new Employee(employeeDto.getEmailAddress()).getName();
    // }
    
    // private String getCourseName(RegistrationDto registrationDto) {
    //     return getNameFromCourseRepository(registrationDto.getCourseID());
    // }
    
    // private String getEmployeeName(RegistrationDto registrationDto) {
    //     return getNameFromEmployeeRepository(registrationDto.getEmailAddress());
    // }
    
    // private boolean validateCourse(String courseId){
    //     Course courseDto=courseRepository.findById(courseId).orElseThrow(()->new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
    //     if(courseDto.isAllotted()) throw new CourseFullException(Constant.COURSE_ALREADY_ALLOTED);
    //     if(courseDto.isCancelled()) throw new InstructorNotFound(Constant.COURSE_CANCELLED);
    //     return true;
    // }

    // private boolean seatsAvailability(String courseId) {
    //     long count=registrationRepository.findById(courseId).stream().filter(RegistrationDto->RegistrationDto.isAccepted()==true).count();
    //     Course courseDto = courseRepository.findById(courseId).get();
    //     long max = courseDto.getMaxCapacity();
    //     return (count<max)?true:false;
    // }

   

}

 // else if (registrationRepository.existsByEmailAndCourseOfferingId(emailId, courseOfferingId)) {
                //     isAccepted = true;
                //     // registrationRepository.save(new Registration(regId, emailId, courseOfferingId, isAccepted));
                //     regId = regId + " ACCEPTED";
                //     return regId;
                // }
