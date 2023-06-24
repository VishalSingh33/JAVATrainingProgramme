// package com.example.fruitShake.service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
// import org.springframework.stereotype.Service;
// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.dto.BookStatus;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.mapper.BookMapper;
// import com.example.fruitShake.repository.BookRepository;
// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.Optional;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class ManageBookService implements BookService {

// 	private final BookRepository bookRepository;
// 	private final BookMapper bookMapper;

// 	@Override
// 	public ResponseEntity<Books> getAllBooks(String bookName, String status) {

// 		if (bookName != null)
// 			bookName = bookName.isEmpty() ? null : "%" + bookName + "%";

// 		Books resBook = bookRepository.findAll(bookName, status);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resBook);
// 	}

// 	@Override
// 	public ResponseEntity<Books> addBook(AddBookRequestDto addBook) {

// 		// Class<UuidGenerator> bookName = UuidGenerator.class;
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		// Books resBooks = bookMapper.setData(bookName, offset, addBook);
// 		ArrayList<Books> list = new ArrayList<>();
// 		Books resBook = new Books();
// 		resBook.setBookName(addBook.getBookName());
// 		resBook.setStatus(BookStatus.AVAILABLE.status());
// 		resBook.setCreatedOn(offset);
// 		resBook.setUpdatedOn(offset);
// 		// resBooks.setbookName(addBook.getbookName());
// 		list.add(resBook);
// 		bookRepository.save(resBook);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resBook);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<Books>> updateBook(String bookName, AddBookRequestDto updateBook) {

// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Optional<Books> resBook = bookRepository.findById(bookName);
// 		resBook.get().setBookName(updateBook.getBookName());
// 		resBook.get().setStatus(BookStatus.AVAILABLE.status());
// 		resBook.get().setUpdatedOn(offset);
// 		bookRepository.save(resBook.get());
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resBook);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<Books>> deleteBook(String bookId) {

// 		// return bookRepository.inactiveById(bookId);
// 		bookRepository.deleteById(bookId);
// 		return  ResponseEntity.status(HttpStatus.CREATED).body(null);
// 	}

// }
