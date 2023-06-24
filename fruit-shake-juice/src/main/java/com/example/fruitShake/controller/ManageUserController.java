// package com.example.fruitShake.controller;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.fruitShake.dto.AddUserRequestDto;
// import com.example.fruitShake.entities.Users;
// import com.example.fruitShake.service.UserService;

// @RestController
// @Slf4j
// @RequiredArgsConstructor
// public class ManageUserController implements UserController {

// 	private final UserService userService;

// 	@Override
// 	public ResponseEntity<Users> getAllUsers(String userName, String mobileNo) {
		
// 		return userService.getAllUsers( userName,  mobileNo);
// 	}

// 	@Override
// 	public ResponseEntity<Users> addUser(AddUserRequestDto userDetails) {

// 		return userService.addUser(userDetails);

// 	}

// 	@Override
// 	public ResponseEntity<Optional<Users>> updateUser(String UserId, AddUserRequestDto updateUser) {
		
// 		return userService.addUser( UserId, updateUser);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<Users>> deleteBook(String userId) {
		
// 		return userService.deleteBook(userId);
// 	}

	

// }
