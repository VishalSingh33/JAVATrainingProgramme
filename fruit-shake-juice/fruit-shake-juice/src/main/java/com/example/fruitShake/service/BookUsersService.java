package com.example.fruitShake.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.Books;
import com.example.fruitShake.entities.Users;

public interface BookUsersService {

    ResponseEntity<List<Users>> getUsersAllDetails();

    ResponseEntity<List<Books>> getBookAllDetails();

    ResponseEntity<BookUsers> issueBooktoUsers(String bookId, String userId);

    ResponseEntity<BookUsers> bookReturn(String bookId, String userId);
    
}
