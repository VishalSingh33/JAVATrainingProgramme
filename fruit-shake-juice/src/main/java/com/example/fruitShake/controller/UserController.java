// package com.example.fruitShake.controller;

// import java.util.Optional;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.fruitShake.dto.AddUserRequestDto;
// import com.example.fruitShake.entities.Users;

// @Validated
// @CrossOrigin("*")
// @RequestMapping("/api/v1")
// public interface UserController {

// 	@GetMapping(value = "/book")
// 	public ResponseEntity<Users> getAllUsers(@RequestParam(required = false) String userName,
// 			@RequestParam(required = false) String mobileNo);

// 	@GetMapping(value = "/users")
// 	public ResponseEntity<Users> addUser(@RequestBody AddUserRequestDto addUser);

// 	@PutMapping(value = "/updateBook")
// 	public ResponseEntity<Optional<Users>> updateUser(@RequestHeader("Z-USERID") String userId,
// 			@RequestBody AddUserRequestDto updateUser);

// 	@DeleteMapping(value = "/deleteUser")
// 	public ResponseEntity<Optional<Users>> deleteBook(@RequestHeader("Z-USERID") String userId);

// }
