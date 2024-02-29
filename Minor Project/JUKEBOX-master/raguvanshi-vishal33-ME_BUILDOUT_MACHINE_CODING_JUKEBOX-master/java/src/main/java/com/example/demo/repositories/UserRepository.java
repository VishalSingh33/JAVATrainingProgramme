package com.example.demo.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Users;

public class UserRepository implements IUserRepository {

    private final Map<String, Users> userMap;
    private Integer autoIncrement = 0;
    private static int counter=0;

    public UserRepository() {
        userMap = new HashMap<String, Users>();
    }

    public UserRepository(Map<String, Users> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public Optional<Users> getUser(String userId) {
        // DONE Auto-generated method stub
        Collection<Users> userlist = userMap.values();
        for(Users user: userlist){
            if(user.getId().equals(userId)){
            return Optional.of(user);
            }
        }
        return Optional.empty();
     }

    @Override
    public Users getUserById(String userId) {
        
        return userMap.get(userId);
    }

    @Override
    public Users saveUser(Users user) {
        try {
            if (userMap.containsKey(user.getId())) {
                    throw new NoSuchCommandException("USER EXIST");
            } else {
                userMap.put(user.getId(), user);
            }
        } catch (NoSuchCommandException e) {
            System.err.println(e);
        }
       return user;
    }
    @Override
    public Users saveUser(String userName) {
          String userId=String.valueOf(++counter);
         return saveUser(new Users(userId,userName));
    }
    
    
}
