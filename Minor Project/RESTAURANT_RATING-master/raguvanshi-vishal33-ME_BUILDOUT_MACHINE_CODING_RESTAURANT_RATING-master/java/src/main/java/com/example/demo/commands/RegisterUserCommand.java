package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.UserServices;

public class RegisterUserCommand implements ICommand {
    private final UserServices userServices;

    public RegisterUserCommand(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        String userName = tokens.get(1);
        userServices.registerUser(userName);
        // System.out.println("Song [id=" + song.getId() + "]");

    }
}
