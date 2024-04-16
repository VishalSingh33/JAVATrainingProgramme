package com.skypro.broadband.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.skypro.broadband.dto.UserDto;
import com.skypro.broadband.entities.User;

public interface UserService {

	public Page<User> getUsers(int page, int size);

	public ResponseEntity<User> createUser(UserDto userDto );

	public ResponseEntity<User> updateUserController(String id, UserDto userDto );

    public void deleteUser(String id);

}
