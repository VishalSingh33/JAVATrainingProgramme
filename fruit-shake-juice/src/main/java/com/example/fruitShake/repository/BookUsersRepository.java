package com.example.fruitShake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fruitShake.entities.BookUsers;


@Repository
public interface BookUsersRepository extends JpaRepository<BookUsers, String>{

}  
