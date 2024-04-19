package com.notification.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.notification.service.dto.UserDto;
import com.notification.service.entity.User;
import com.notification.service.service.ManageUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageUserController implements UserController {

	@Autowired
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