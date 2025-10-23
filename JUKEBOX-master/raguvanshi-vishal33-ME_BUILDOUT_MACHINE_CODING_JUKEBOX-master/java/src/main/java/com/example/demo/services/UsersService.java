package com.example.demo.services;

import com.example.demo.entities.Users;
import com.example.demo.repositories.IUserRepository;

public class UsersService implements IUserService {

    private final IUserRepository userRepository;

    public UsersService(IUserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public Users createUser(String userId) {
        // DONE Auto-generated method stub
        return userRepository.saveUser(userId);
    }


}
