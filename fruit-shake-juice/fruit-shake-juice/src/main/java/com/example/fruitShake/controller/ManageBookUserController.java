package com.example.fruitShake.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.fruitShake.dto.AddBookUSerRequestDto;
import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.Books;
import com.example.fruitShake.service.BookService;
import com.example.fruitShake.service.BookUsersService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ManageBookUserController implements BookUserController {

	private final BookUsersService bookUsersService;

	@Override
	public ResponseEntity<BookUsers> getBookUsersAllDetails(String bookId, String userId) {

		return bookUsersService.getBookUsersAllDetails(bookId, userId);
	}
}
