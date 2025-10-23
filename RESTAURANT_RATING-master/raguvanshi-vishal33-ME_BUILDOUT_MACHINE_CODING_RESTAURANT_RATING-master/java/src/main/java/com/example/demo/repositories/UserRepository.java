package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.entities.User;

public class UserRepository implements IUserRepositories {
    private final Map<Long, User> userMap;
    private Long autoIncreament = 1L;

    public UserRepository() {
        this.userMap = new HashMap<>();
    }

    @Override
    public User save(User user) {
        User users = new User(autoIncreament, user.getName());
        userMap.put(autoIncreament++, users);
        return users;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByName(String userName) {
        for (Map.Entry<Long, User> entry : userMap.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(userName))
                return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty();
    }

    @Override
    public void delete(User user) {
        userMap.remove(user.getId());
    }

    @Override
    public int count() {
        return userMap.size();
    }

    // @Override
    // public boolean userExists(User user) {

    //     return false;
    // }
    
}
