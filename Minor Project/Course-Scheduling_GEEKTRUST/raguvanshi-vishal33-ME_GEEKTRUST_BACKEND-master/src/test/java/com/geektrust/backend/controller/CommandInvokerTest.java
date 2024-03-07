// package com.geektrust.backend.controller;

// import static org.mockito.ArgumentMatchers.anyList;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @DisplayName("CommandInvokerTest")
// @ExtendWith(MockitoExtension.class)
// public class CommandInvokerTest {
//     private CommandInvoker CommandInvoker;

//     @Mock
//     AddCourseController addCourseController;
//     @Mock
//     RegisterCourseController registerController;
//     @Mock
//     AllotCourseController allotController;    
//     @Mock
//     CancelCourseController cancelController;

//     @BeforeEach
//     void setup(){
//         CommandInvoker = new CommandInvoker();
       
//         CommandInvoker.register("ADD-COURSE-OFFERING",addCourseController);
//         CommandInvoker.register("REGISTER",registerController);
//         CommandInvoker.register("ALLOT",allotController);
//         CommandInvoker.register("CANCEL",cancelController);
       
//     }

//     @Test
//     @DisplayName("executeController method Should Execute Controller Given ControllerName and List of tokens")
//     public void executeController_GivenNameAndTokens_ShouldExecuteController() {
//         CommandInvoker.executecommand("ADD-COURSE-OFFERING",anyList());
//         CommandInvoker.executecommand("REGISTER",anyList());
//         CommandInvoker.executecommand("ALLOT",anyList());
//         CommandInvoker.executecommand("CANCEL",anyList());       
        
       
//     }
// }