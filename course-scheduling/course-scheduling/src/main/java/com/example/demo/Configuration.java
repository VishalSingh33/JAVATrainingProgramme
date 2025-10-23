package com.example.demo;

import com.example.demo.commands.AddCourseController;
import com.example.demo.commands.AllotCourseController;
import com.example.demo.commands.CancelCourseController;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.RegisterCourseController;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.ICourseRepository;
import com.example.demo.repositories.IEmployeeRepository;
import com.example.demo.repositories.IRegistrationRepository;
import com.example.demo.repositories.RegistrationRepository;
import com.example.demo.services.CourseService;
import com.example.demo.services.ICourseService;
import com.example.demo.services.IRegistrationService;
import com.example.demo.services.RegistrationService;

public class Configuration {
    // Singleton Pattern
    //create an object of Single Configuration Object
    private static Configuration instance = new Configuration();

    //make the constructor private so that this class cannot be
    //instantiated
    private Configuration(){}

    //Get the only object available
    public static Configuration getInstance(){
        return instance;
    }

    // Initialize repositories
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

    // public CommandRegistry getCommandInvoker(){
        private void registerCommands(){
        commandInvoker.register(CommandKeyword.ADD_COURSE_OFFERING.getName(), addCourseOfferingController);
        commandInvoker.register(CommandKeyword.REGISTER.getName(), registerController);
        commandInvoker.register(CommandKeyword.ALLOT.getName(), allotController);
        commandInvoker.register(CommandKeyword.CANCEL.getName(), cancelController);
            }
            
            public CommandRegistry getCommandRegistry(){
                registerCommands();
                return commandInvoker;
            }
}
