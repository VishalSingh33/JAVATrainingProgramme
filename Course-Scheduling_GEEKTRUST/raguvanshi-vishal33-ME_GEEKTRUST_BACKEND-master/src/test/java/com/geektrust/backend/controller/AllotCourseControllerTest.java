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

@DisplayName("Allot Controller Test")
@ExtendWith(MockitoExtension.class)
public class AllotCourseControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    ICourseService courseServiceMock;

    @InjectMocks
    AllotCourseController allotCourseController;

    @InjectMocks
    RegisterCourseController registerCourseController;

    @InjectMocks
    AddCourseController addCourseController;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Allot Course Controller Functional Test")
    public void execute_AllotCourseController() {
        //Arrange
       
        // ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3
        // REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
        // REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        // ALLOT OFFERING-DATASCIENCE-BOB
        
        String expectedOutput="null\n"+"null\n"+"null\n"+
        "REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN 05062022 CONFIRMED\n"+
        "REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN  05062022 CONFIRMED"; 
       
             
        //String emailAddress="ANDY@GMAIL.COM";
        String courseOfferingId="OFFERING-DATASCIENCE-BOB";
       
        addCourseController.execute(List.of("ADD-COURSE-OFFERING","DATASCIENCE","BOB", "05062022" ,"1" ,"3"));
        registerCourseController.execute(List.of("REGISTER","WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB"));
        registerCourseController.execute(List.of("REGISTER","ANDY@GMAIL.COM", "OFFERING-DATASCIENCE-BOB"));
         
     
        //Act
        String result="REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN 05062022 CONFIRMED\n"+
        "REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN  05062022 CONFIRMED"; 
       
        when(courseServiceMock.allotCourse(courseOfferingId)).thenReturn(result);
        allotCourseController.execute(List.of("ALLOT","OFFERING-DATASCIENCE-BOB"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        
    }
    

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
 
}