package com.skypro.broadband.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skypro.broadband.entities.User;
import com.skypro.broadband.dto.UserDto;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/admin/v1")
public interface UserController {

    @GetMapping(value = "/users-status")
	public Page<User> getUsers(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size);

	@GetMapping(value = "/users-status/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id);

	@PostMapping(value = "/users-status")
	public ResponseEntity<User> createUser(@RequestBody UserDto userDto );

	@PutMapping("/users-status/{id}")
	public ResponseEntity<User> updateUserController(@PathVariable String id, @RequestBody UserDto userDto );

    @DeleteMapping("/users-status/{id}")
    public void deleteUser(@PathVariable String id);

}