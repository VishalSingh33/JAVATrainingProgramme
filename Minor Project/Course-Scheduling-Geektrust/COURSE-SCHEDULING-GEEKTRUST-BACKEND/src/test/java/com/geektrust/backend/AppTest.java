package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AppTest")
public class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Input Controllers Test for input1.txt file")
    void runTest1(){

        //Arrange
        List<String> arguments= new ArrayList<>(List.of("INPUT_FILE=sample_input/input1.txt"));

        String expectedOutput = "OFFERING-DATASCIENCE-BOB\n"+
        "REG-COURSE-WOO-DATASCIENCE ACCEPTED\n"+
        "REG-COURSE-ANDY-DATASCIENCE ACCEPTED\n"+
        "REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"+
        "REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED";
        //Act
        App.run(arguments);
        String actualOutput = outputStreamCaptor.toString().replaceAll("\\s", "");

        //Assert
        Assertions.assertEquals(expectedOutput.replaceAll("\\s", ""), actualOutput);
    
 
	}


    @Test
    @DisplayName("Input Controllers Test for input2.txt file")
    void runTest2(){

        //Arrange
        List<String> arguments= new ArrayList<>(List.of("INPUT_FILE=sample_input/input2.txt"));

        String expectedOutput = "OFFERING-PYTHON-JOHN\n"+
        "REG-COURSE-WOO-PYTHON ACCEPTED\n"+
        "REG-COURSE-ANDY-PYTHON ACCEPTED\n"+
        "REG-COURSE-BOBY-PYTHON ACCEPTED\n"+
        "REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED\n"+
        "REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED\n"+          
        "REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED";
        
        //Act
        App.run(arguments);
        String actualOutput = outputStreamCaptor.toString().replaceAll("\\s", "");

        //Assert
        Assertions.assertEquals(expectedOutput.replaceAll("\\s", ""), actualOutput);
    
 
	}

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
