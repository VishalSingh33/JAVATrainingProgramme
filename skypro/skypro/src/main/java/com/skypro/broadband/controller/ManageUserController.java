package com.skypro.broadband.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.skypro.broadband.dto.UserDto;
import com.skypro.broadband.entities.User;
import com.skypro.broadband.services.ManageUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageUserController implements UserController {

	private final ManageUserService userService;

	@Override
	public Page<User> getUsers(int page, int size) {

		return userService.getUsers(page, size);
	}

	@Override
	public ResponseEntity<User> getUserById(String id) {
		
		return userService.getUserById(id);
	}

	@Override
	public ResponseEntity<User> createUser(UserDto userDto) {

		return userService.createUser(userDto);
	}

	@Override
	public ResponseEntity<User> updateUserController(String id, UserDto userDto) {

		return userService.updateUserController(id, userDto);
	}

	@Override
	public void deleteUser(String id) {

		userService.deleteUser(id);
	}

}