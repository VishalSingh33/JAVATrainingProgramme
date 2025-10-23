package com.geektrust.backend.services;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.geektrust.backend.entities.Course;
import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.entities.Registration;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistrationServiceTest {

    private RegistrationService registrationService;
    
    @Mock
    private ICourseRepository courseRepository;
    
    @Mock
    private IEmployeeRepository employeeRepository;
    
    @Mock
    private IRegistrationRepository registrationRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(courseRepository, employeeRepository, registrationRepository);
    }
    
    @Test
    @DisplayName("Test create() method with valid inputs")
    public void testCreateWithValidInputs() {
    String courseName="JAVA";
    String instructor="JAMES";
    String date="15062022";
    int minCapacity=1;
    int maxCapacity=2;
    boolean isAllotted=false;
    boolean isCancelled=false;
    String courseID="OFFERING-"+courseName+"-"+instructor;
  
    Course course = new Course(courseID, courseName, instructor, date, minCapacity, maxCapacity, isAllotted, isCancelled);
    Employee employee = new Employee("ANDY@GMAIL.COM");

        when(courseRepository.findCourseInfo(courseID)).thenReturn(course);
        String emailId="ANDY@GMAIL.COM";

        when(employeeRepository.findNameByEmail(emailId)).thenReturn(employee);       
        String expectedOutput = "REG-COURSE-ANDY-JAVA ACCEPTED";
        String actualOutput = registrationService.create(emailId, courseID);
        assertEquals(expectedOutput, actualOutput);
        verify(employeeRepository, times(1)).save(employee);
        verify(registrationRepository, times(1)).save(any(Registration.class));
    }
    
    @Test
    @DisplayName("Test create() method with invalid email address")
    void testCreateWithInvalidEmail() {
        String emailId = "ANDY";
        String courseId = "OFFERING-JAVA-JAMES";
        assertThrows(InvalidInputException.class, () -> {
            registrationService.create(emailId, courseId);
        });
        verify(employeeRepository, never()).save(any(Employee.class));
        verify(registrationRepository, never()).save(any(Registration.class));
    }
    
    @Test
    @DisplayName("Test create() method with missing email")
    void testCreateWithMissingEmail() {
        String emailId = null;
        String courseId = "OFFERING-JAVA-JAMES";
        assertThrows(InvalidInputException.class, () -> {
            registrationService.create(emailId, courseId);
        });
        verify(employeeRepository, never()).save(any(Employee.class));
        verify(registrationRepository, never()).save(any(Registration.class));
    }
    
    @Test
    @DisplayName("Test create() method with missing course offering ID")
    void testCreateWithMissingCourseOfferingId() {
        String emailId = "ANDY@GMAIL.COM";
        String courseId = null;
        assertThrows(InvalidInputException.class, () -> {
            registrationService.create(emailId, courseId);
        });
       
        verify(registrationRepository, never()).save(any(Registration.class));
    }
    
}