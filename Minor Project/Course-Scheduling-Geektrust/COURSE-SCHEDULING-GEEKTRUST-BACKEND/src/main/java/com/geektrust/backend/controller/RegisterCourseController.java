package com.geektrust.backend.controller;

import java.util.List;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRegistrationService;

public class RegisterCourseController implements IController{

    private final IRegistrationService registrationService;
    
    public RegisterCourseController(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }
      
    // IP:- <email-id> <course-offering-id>
    // OP:-	<course-registration-id> <status>
    // Sample Input Token List:- ["REGISTER","ANDY@GMAIL.COM","OFFERING-JAVA-JAMES"]

    @Override
    public void execute(List<String> tokens) {
       // System.out.println(tokens); 
        try {
            
            if (tokens.size() < 2) {
                throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
            }

            String emailAddress = tokens.get(1);
            String courseOfferingId = "";
            if (tokens.size() > 2) {
                 emailAddress = tokens.get(1);
                 courseOfferingId = tokens.get(2);
            }
           
            if (emailAddress == null || emailAddress.isEmpty() || courseOfferingId == null || courseOfferingId.isEmpty()) {
               // throw new InvalidInputException("INPUT_DATA_ERROR\n(because email and  course-offering-id missing)\n");
               throw new InvalidInputException("INPUT_DATA_ERROR");
            }
                               
            String result= registrationService.create(emailAddress, courseOfferingId);
            System.out.println(result);          
            
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}