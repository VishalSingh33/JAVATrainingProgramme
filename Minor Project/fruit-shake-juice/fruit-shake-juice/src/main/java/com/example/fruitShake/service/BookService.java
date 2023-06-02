package com.example.fruitShake.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.fruitShake.dto.AddBookUSerRequestDto;
import com.example.fruitShake.entities.Books;

public interface BookService {

    ResponseEntity<Books> getAllBooks(String bookName, String status);

    ResponseEntity<Books> addBook(AddBookUSerRequestDto addBook);

    ResponseEntity<Optional<Books>> updateBook(String bookId, AddBookUSerRequestDto updateBook);

    ResponseEntity<Optional<Books>> deleteBook(String bookId);

}
