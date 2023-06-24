package com.example.fruitShake.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.example.fruitShake.dto.AddBookRequestDto;
import com.example.fruitShake.dto.BookStatus;
import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.Books;
import com.example.fruitShake.entities.Users;
import com.example.fruitShake.repository.BookRepository;
import com.example.fruitShake.repository.BookUsersRepository;
import com.example.fruitShake.repository.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManageBookUsersService implements BookUsersService {

    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;
    private final BookUsersRepository bookUsersRepository;

    @Override
    public ResponseEntity<List<Users>> getUsersAllDetails() {

        List<Users> listUsers = usersRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(listUsers);
    }

    @Override
    public ResponseEntity<List<Books>> getBookAllDetails() {

        List<Books> listBooks = bookRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(listBooks);
    }

    @Override
    public ResponseEntity<BookUsers> issueBooktoUsers(String bookId, String userId) {

        Books books = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + userId));

        OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
        BookUsers bookUsers = new BookUsers();
        bookUsers.setBookId(books);
        bookUsers.setUserId(users);
        bookUsers.setIssuedDate(offset);
        bookUsers.setReturnDate(offset.plusDays(14));
        bookUsers.setStatus(BookStatus.BOOKED.status());
        books.setStatus(BookStatus.BOOKED.status());
        bookUsersRepository.save(bookUsers);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookUsers);
    }

    @Override
    public ResponseEntity<BookUsers> bookReturn(String bookId, String userId) {

        Books books = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + userId));

        OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
        BookUsers bookUsers = new BookUsers();
        bookUsers.setReturnDate(offset);
        bookUsers.setStatus(BookStatus.AVAILABLE.status());
        books.setStatus(BookStatus.AVAILABLE.status());
        bookUsersRepository.save(bookUsers);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookUsers);
    }

}
