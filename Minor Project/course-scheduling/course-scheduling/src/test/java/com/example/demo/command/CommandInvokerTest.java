package com.example.demo.command;

import com.example.demo.commands.AddCourseController;
import com.example.demo.commands.AllotCourseController;
import com.example.demo.commands.CancelCourseController;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.RegisterCourseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandRegistry CommandInvoker;

    @Mock
    AddCourseController addCourseController;
    @Mock
    RegisterCourseController registerController;
    @Mock
    AllotCourseController allotController;    
    @Mock
    CancelCourseController cancelController;

    @BeforeEach
    void setup(){
        CommandInvoker = new CommandRegistry();
       
        CommandInvoker.register("ADD-COURSE-OFFERING",addCourseController);
        CommandInvoker.register("REGISTER",registerController);
        CommandInvoker.register("ALLOT",allotController);
        CommandInvoker.register("CANCEL",cancelController);
       
    }

    @Test
    @DisplayName("executeController method Should Execute Controller Given ControllerName and List of tokens")
    public void executeController_GivenNameAndTokens_ShouldExecuteController() {
        // CommandInvoker.invokeCommand("ADD-COURSE-OFFERING",anyList());
        // CommandInvoker.executecommand("REGISTER",anyList());
        // CommandInvoker.executecommand("ALLOT",anyList());
        // CommandInvoker.executecommand("CANCEL",anyList());       
        
       
    }
}