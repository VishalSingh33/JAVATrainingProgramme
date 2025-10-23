package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.IUserRepositories;

public class UserServices {
    private final IUserRepositories userRepo;

    public UserServices(IUserRepositories userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(String userName) {
        User user = userRepo.save(new User(userName));
        System.out.println(user);
    }
}