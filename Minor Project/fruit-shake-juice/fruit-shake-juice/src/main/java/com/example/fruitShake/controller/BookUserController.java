package com.example.fruitShake.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fruitShake.dto.AddBookUSerRequestDto;
import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.Books;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/v1")
public interface BookUserController {

	@GetMapping(value = "/getBookUsersAll")
	public ResponseEntity<BookUsers> getBookUsersAllDetails(String bookId, String userId);

}
