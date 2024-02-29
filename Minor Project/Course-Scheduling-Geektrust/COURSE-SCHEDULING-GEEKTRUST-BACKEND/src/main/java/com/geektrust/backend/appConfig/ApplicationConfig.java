package com.geektrust.backend.appConfig;

import com.geektrust.backend.controller.AddCourseController;
import com.geektrust.backend.controller.AllotCourseController;
import com.geektrust.backend.controller.CancelCourseController;
import com.geektrust.backend.controller.ControllerInvoker;
import com.geektrust.backend.controller.RegisterCourseController;
import com.geektrust.backend.repositories.CourseRepository;
import com.geektrust.backend.repositories.EmployeeRepository;
import com.geektrust.backend.repositories.ICourseRepository;
import com.geektrust.backend.repositories.IEmployeeRepository;
import com.geektrust.backend.repositories.IRegistrationRepository;
import com.geektrust.backend.repositories.RegistrationRepository;
import com.geektrust.backend.services.CourseService;
import com.geektrust.backend.services.ICourseService;
import com.geektrust.backend.services.IRegistrationService;
import com.geektrust.backend.services.RegistrationService;

public class ApplicationConfig {
    private final ICourseRepository courseRepository = new CourseRepository();
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();
    private final IRegistrationRepository registrationRepository = new RegistrationRepository();
       
    private final ICourseService courseService = new CourseService(courseRepository, employeeRepository,registrationRepository);
    private final IRegistrationService registrationService = new RegistrationService(courseRepository, employeeRepository, registrationRepository);


    private final AddCourseController addCourseOfferingController = new AddCourseController(courseService);
    private final RegisterCourseController registerController = new RegisterCourseController(registrationService);
    private final AllotCourseController allotController = new AllotCourseController(courseService);
    private final CancelCourseController cancelController = new CancelCourseController(courseService);
    
    private final ControllerInvoker controllerInvoker = new ControllerInvoker();

    public ControllerInvoker getControllerInvoker(){
        controllerInvoker.register("ADD-COURSE-OFFERING",addCourseOfferingController);
        controllerInvoker.register("REGISTER",registerController);
        controllerInvoker.register("ALLOT",allotController);
        controllerInvoker.register("CANCEL",cancelController);
       
       
        return controllerInvoker;
    }



    
}