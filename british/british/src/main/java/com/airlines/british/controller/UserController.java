package com.airlines.british.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.airlines.british.dto.UserDto;
import com.airlines.british.entites.User;

@Validated
@CrossOrigin("*")
@RequestMapping("/todos")
public interface UserController {

	@GetMapping(value = "/users")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size);

	@GetMapping(value = "/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId);

	@PostMapping(value = "/users")
	public ResponseEntity<User> createUser(@RequestBody UserDto userDto);

	@PutMapping(value = "/users/{userId}")
	public ResponseEntity<User> updateUserController(@PathVariable String userId, @RequestBody UserDto userDto);

	@DeleteMapping(value = "/users/{userId}")
	public void deleteUser(@PathVariable String userId);


	// // http://localhost:8080/api/user/v1/1
	// @GetMapping(value = "/")
	// public List<Todo> getSingleTodo();

}