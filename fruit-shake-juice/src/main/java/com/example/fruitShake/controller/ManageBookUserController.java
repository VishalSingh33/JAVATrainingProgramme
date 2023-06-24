// package com.example.fruitShake.controller;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.entities.BookUsers;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.Users;
// import com.example.fruitShake.service.BookService;
// import com.example.fruitShake.service.BookUsersService;
// import com.example.fruitShake.service.UserService;

// @RestController
// @Slf4j
// @RequiredArgsConstructor
// public class ManageBookUserController implements BookUserController {

// 	private final BookService bookService;
// 	private final UserService userService;
// 	private final BookUsersService bookUsersService;

// 	@Override
// 	public ResponseEntity<List<Users>> getUsersAllDetails() {

// 		return bookUsersService.getUsersAllDetails();
// 	}

// 	@Override
// 	public ResponseEntity<List<Books>> getBookAllDetails() {

// 		return bookUsersService.getBookAllDetails();
// 	}

// 	@Override
// 	public ResponseEntity<BookUsers> issueBooktoUsers(String bookId, String userId) {

// 		return bookUsersService.issueBooktoUsers(bookId, userId);
// 	}

// 	@Override
// 	public ResponseEntity<BookUsers> bookReturn(String bookId, String userId) {

// 		return bookUsersService.bookReturn(bookId, userId);
// 	}

// }
