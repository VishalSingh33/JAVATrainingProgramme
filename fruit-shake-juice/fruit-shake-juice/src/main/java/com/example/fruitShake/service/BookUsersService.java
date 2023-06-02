package com.example.fruitShake.service;

import org.springframework.http.ResponseEntity;

import com.example.fruitShake.entities.BookUsers;

public interface BookUsersService {

    ResponseEntity<BookUsers> getBookUsersAllDetails(String bookId, String userId);
    
}
