package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Users;
import com.example.demo.services.IUserService;

public class CreateUserCommand implements ICommand {
    private final IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }
    @Override
    public void invoke(List<String> tokens) {
        
        String input = tokens.get(1);
        Users createdUser = userService.createUser(input);
        System.out.print(createdUser.getId() +" "+createdUser.getUserName());
    }
}