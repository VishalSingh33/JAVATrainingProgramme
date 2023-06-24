// package com.example.fruitShake.controller;

// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.entities.Books;

// @Validated
// @CrossOrigin("*")
// @RequestMapping("/api/v1")
// public interface BookController {

// 	@GetMapping(value = "/book")
// 	public ResponseEntity<Books> getAllBooks(@RequestParam(required = false) String bookName,
// 			@RequestParam(required = false) String status);

// 	@PostMapping(value = "/createBook")
// 	public ResponseEntity<Books> addBook(@RequestBody AddBookRequestDto addBook);

// 	@PutMapping(value = "/updateBook")
// 	public ResponseEntity<Optional<Books>> updateBook(@RequestHeader("Z-BOOKID") String bookId,
// 			@RequestBody AddBookRequestDto updateBook);

// 	@DeleteMapping(value = "/deleteBook")
// 	public ResponseEntity<Optional<Books>> deleteBook(@RequestHeader("Z-BOOKID") String bookId);

// }
