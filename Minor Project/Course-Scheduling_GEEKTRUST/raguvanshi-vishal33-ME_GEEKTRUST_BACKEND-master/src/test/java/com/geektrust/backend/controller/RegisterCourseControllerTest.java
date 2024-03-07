package com.geektrust.backend.controller;

import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.geektrust.backend.services.ICourseService;
import com.geektrust.backend.services.IRegistrationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Register Course Controller Test")
@ExtendWith(MockitoExtension.class)
public class RegisterCourseControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    ICourseService courseServiceMock;

    @Mock
    IRegistrationService registrationServiceMock;

    @InjectMocks
    RegisterCourseController registerCourseController;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Register Course Controller Functional Test")
    public void execute_ShouldPrintRegisterEmployeeCourse() {
        //Arrange
        String expectedOutput = "REG-COURSE-ANDY-JAVA ACCEPTED";       
              
        String emailAddress="ANDY@GMAIL.COM";
        String courseOfferingId="OFFERING-JAVA-JAMES";
     
        //Act
        String regId="REG-COURSE-ANDY-JAVA ACCEPTED"; 

        when(registrationServiceMock.create(emailAddress, courseOfferingId)).thenReturn(regId);
        registerCourseController.execute(List.of("REGISTER","ANDY@GMAIL.COM","OFFERING-JAVA-JAMES"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
 
}