//package com.example.fruitShake.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.fruitShake.dto.AddBookRequestDto;
//import com.example.fruitShake.entities.BookUsers;
//import com.example.fruitShake.entities.Books;
//// import com.example.fruitShake.entities.Books;
//import com.example.fruitShake.entities.Users;
//
//@Validated
//@CrossOrigin("*")
//@RequestMapping("/api/v1")
//public interface BookUserController {
//
//	@GetMapping(value = "/getAllUsers")
//	public ResponseEntity<List<Users>> getUsersAllDetails();
//
//	@GetMapping(value = "/getAllBooks")
//	public ResponseEntity<List<Books>> getBookAllDetails();
//
//	@PostMapping(value = "/issue/{userId}/{bookId}")
//	public ResponseEntity<BookUsers> issueBooktoUsers(String bookId, String userId);
//
//	@PutMapping(value = "/returned/{userId}/{bookId}")
//	public ResponseEntity<BookUsers> bookReturn(String bookId, String userId);
//
//}
