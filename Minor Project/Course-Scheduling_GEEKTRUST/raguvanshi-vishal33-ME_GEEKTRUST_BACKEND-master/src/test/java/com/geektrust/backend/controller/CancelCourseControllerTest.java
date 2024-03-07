package com.geektrust.backend.controller;

import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.geektrust.backend.services.ICourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Cancel Course Controller Test")
@ExtendWith(MockitoExtension.class)
public class CancelCourseControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    ICourseService courseServiceMock;

    @InjectMocks
    CancelCourseController cancelCourseController;

    @InjectMocks
    RegisterCourseController registerCourseController;

    @InjectMocks
    AddCourseController addCourseController;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Cancel Course Controller Functional Test")
    public void execute_CancelCourseController() {
        //Arrange
       
        // ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3
        // REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
        // CANCEL REG-COURSE-BOBY-PYTHON
      
        
        String expectedOutput="null\n"+"null\n"+
        "REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED";
         
       
             
        //String emailAddress="ANDY@GMAIL.COM";
        String regId="REG-COURSE-BOBY-PYTHON";
       
        addCourseController.execute(List.of("ADD-COURSE-OFFERING","DATASCIENCE","BOB", "05062022" ,"1" ,"3"));
        registerCourseController.execute(List.of("REGISTER","WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB"));
         
     
        //Act
        String result="REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED"; 
       
        when(courseServiceMock.cancelCourse(regId)).thenReturn(result);
        cancelCourseController.execute(List.of("CANCEL","REG-COURSE-BOBY-PYTHON"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        
    }
    

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
 
}