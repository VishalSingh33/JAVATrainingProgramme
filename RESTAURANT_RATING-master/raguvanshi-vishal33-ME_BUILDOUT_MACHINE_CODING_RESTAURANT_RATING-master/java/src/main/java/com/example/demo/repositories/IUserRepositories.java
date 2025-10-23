package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.User;

public interface IUserRepositories {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByName(String userName);

    void delete(User user);

    int count();

     // boolean userExists(User user);

}
