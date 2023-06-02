package com.example.fruitShake.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.fruitShake.dto.AddBookUSerRequestDto;
import com.example.fruitShake.entities.Books;
import com.example.fruitShake.service.BookService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ManageBookController implements BookController {

	private final BookService bookService;

	@Override
	public ResponseEntity<Books> getAllBooks(String bookName, String status) {

		return bookService.getAllBooks(bookName, status);
	}


	@Override
	public ResponseEntity<Books> addBook(AddBookUSerRequestDto addBook) {

		return bookService.addBook(addBook);
	}

	@Override
	public ResponseEntity<Optional<Books>> updateBook(String bookId, AddBookUSerRequestDto updateBook) {
		
		return bookService.updateBook(bookId, updateBook);
	}


	@Override
	public ResponseEntity<Optional<Books>> deleteBook(String bookId) {
		
		return bookService.deleteBook(bookId);
	}

	// @Override
	// public ResponseEntity<Response> blockUser(String fromUserId, BlockUserDto
	// blockUserDto) {

	// return manageBlockService.blockUser(fromUserId, blockUserDto);
	// }

	// @Override
	// public ResponseEntity<Response> unBlockUser(String fromUserId, BlockUserDto
	// blockUserDto) {

	// return manageBlockService.unBlockUser(fromUserId, blockUserDto);
	// }

	// @Override
	// public ResponseEntity<Response> blockedUserList(String fromUserId,
	// ListingRequest listingRequest) {

	// return manageBlockService.blockedUserList(fromUserId, listingRequest);
	// }

}
