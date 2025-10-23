package com.geektrust.backend;

import com.geektrust.backend.controller.AddCourseController;
import com.geektrust.backend.controller.AllotCourseController;
import com.geektrust.backend.controller.CancelCourseController;
// import com.geektrust.backend.controller.CommandInvoker;
import com.geektrust.backend.controller.CommandKeyword;
import com.geektrust.backend.controller.CommandRegistry;
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

    // Singleton Pattern
    // create an object of Single Configuration Object
    private static ApplicationConfig instance = new ApplicationConfig();

    // make the constructor private so that this class cannot be
    // instantiated
   private ApplicationConfig() {}

    // Get the only object available
    public static ApplicationConfig getInstance() {
        return instance;
    }


    private final ICourseRepository courseRepository = new CourseRepository();
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();
    private final IRegistrationRepository registrationRepository = new RegistrationRepository();
       
    private final ICourseService courseService = new CourseService(courseRepository,registrationRepository);
    private final IRegistrationService registrationService = new RegistrationService(courseRepository, employeeRepository, registrationRepository);


    private final AddCourseController addCourseOfferingController = new AddCourseController(courseService);
    private final RegisterCourseController registerController = new RegisterCourseController(registrationService);
    private final AllotCourseController allotController = new AllotCourseController(courseService);
    private final CancelCourseController cancelController = new CancelCourseController(courseService);


    // Initialize commandRegistery
    // private final CommandInvoker commandInvoker = new CommandInvoker();
    private final CommandRegistry commandInvoker = new CommandRegistry();

    public CommandRegistry getCommandInvoker(){
        commandInvoker.register(CommandKeyword.ADD_COURSE_OFFERING.getName(), addCourseOfferingController);
        commandInvoker.register(CommandKeyword.REGISTER.getName(), registerController);
        commandInvoker.register(CommandKeyword.ALLOT.getName(), allotController);
        commandInvoker.register(CommandKeyword.CANCEL.getName(), cancelController);
       
        return commandInvoker;
    }

}
