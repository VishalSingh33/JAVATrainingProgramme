package com.example.fruitShake.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.fruitShake.dto.AddBookUSerRequestDto;
import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.Books;
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
    public ResponseEntity<BookUsers> getBookUsersAllDetails(String bookId, String userId) {


        // Books book = bookRepository.
      
      return null;
    }
    
    
    
}
