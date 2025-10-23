package com.geektrust.backend.controller;

import java.util.List;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.services.IRegistrationService;

public class RegisterCourseController implements ICommand {

    private final IRegistrationService registrationService;

    public RegisterCourseController(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    // IP:- <email-id> <course-offering-id>
    // OP:- <course-registration-id> <status>
    // Sample Input Token List:- ["REGISTER","ANDY@GMAIL.COM","OFFERING-JAVA-JAMES"]

    @Override
    public void execute(List<String> tokens) {
        // System.out.println(tokens);
        try {

            if (tokens.size() < IndexCommand.TWO_INDEX.getIndex()) {
                throw new InvalidInputException("INPUT_DATA_ERROR (not enough input values)");
            }

            String emailAddress = tokens.get(IndexCommand.ONE_INDEX.getIndex());
            String courseOfferingId = "";
            if (tokens.size() > IndexCommand.TWO_INDEX.getIndex()) {
            emailAddress = tokens.get(IndexCommand.ONE_INDEX.getIndex());
            courseOfferingId = tokens.get(IndexCommand.TWO_INDEX.getIndex());
            }

            if (emailAddress == null || emailAddress.isEmpty() || courseOfferingId == null || courseOfferingId.isEmpty()) {

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
