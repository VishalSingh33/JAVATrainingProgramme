package com.airlines.british.controller;

import com.airlines.british.dto.UserDto;
import com.airlines.british.entites.User;
import com.airlines.british.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageUserController implements UserController {

	@Autowired
	private final UserService userService;

	@Override
	public Page<User> getUsers(int page, int size) {

		return userService.getUsers(page, size);
	}

	@Override
	public ResponseEntity<User> getUserById(String userId) {
		
		return userService.getUserById(userId);
	}

	@Override
	public ResponseEntity<User> createUser(UserDto userDto) {

		return userService.createUser(userDto);
	}

	@Override
	public ResponseEntity<User> updateUserController(String userId, UserDto userDto) {

		return userService.updateUserController(userId, userDto);
	}

	@Override
	public void deleteUser(String id) {

		userService.deleteUser(id);
	}

}