package com.geektrust.backend.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.geektrust.backend.entities.Course;
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

@DisplayName("Add Course Controller Test")
@ExtendWith(MockitoExtension.class)
public class AddCourseControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    ICourseService courseServiceMock;

    @InjectMocks
    AddCourseController addCourseController;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of CreateCourseController Should Print newly Created Course To Console")
    public void execute_ShouldPrintCourse() {
        //Arrange
      
        String expectedOutput = "OFFERING-JAVA-JAMES";        
        String courseName="JAVA";
        String instructor="JAMES";
        String date="15062022";
        int minCapacity=1;
        int maxCapacity=2;
        boolean isAllotted=false;
        boolean isCancelled=false;
        String courseID="OFFERING-"+courseName+"-"+instructor;
        Course course = new Course(courseID, courseName, instructor, date, minCapacity, maxCapacity,isAllotted,isCancelled);
        when(courseServiceMock.create(courseName, instructor, date, minCapacity, maxCapacity)).thenReturn(course);
        
        //Act
        addCourseController.execute(List.of("ADD-COURSE-OFFERING","JAVA","JAMES","15062022","1","2"));

       

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(courseServiceMock,times(1)).create(courseName, instructor, date, minCapacity, maxCapacity);
    }
    

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
 
}