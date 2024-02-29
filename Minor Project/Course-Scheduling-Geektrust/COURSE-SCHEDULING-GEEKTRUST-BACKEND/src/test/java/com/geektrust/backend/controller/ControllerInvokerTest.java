package com.geektrust.backend.controller;

import static org.mockito.ArgumentMatchers.anyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ControllerInvokerTest")
@ExtendWith(MockitoExtension.class)
public class ControllerInvokerTest {
    private ControllerInvoker ControllerInvoker;

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
        ControllerInvoker = new ControllerInvoker();
       
        ControllerInvoker.register("ADD-COURSE-OFFERING",addCourseController);
        ControllerInvoker.register("REGISTER",registerController);
        ControllerInvoker.register("ALLOT",allotController);
        ControllerInvoker.register("CANCEL",cancelController);
       
    }

    @Test
    @DisplayName("executeController method Should Execute Controller Given ControllerName and List of tokens")
    public void executeController_GivenNameAndTokens_ShouldExecuteController() {
        ControllerInvoker.executeController("ADD-COURSE-OFFERING",anyList());
        ControllerInvoker.executeController("REGISTER",anyList());
        ControllerInvoker.executeController("ALLOT",anyList());
        ControllerInvoker.executeController("CANCEL",anyList());       
        
       
    }
}
