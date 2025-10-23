package com.example.demo.repositories;

import java.util.Optional;
import com.example.demo.entities.Users;

public interface IUserRepository {

    Optional<Users> getUser(String userId);

    Users getUserById(String userId);

    Users saveUser(Users user);

    Users saveUser(String userName);
}

