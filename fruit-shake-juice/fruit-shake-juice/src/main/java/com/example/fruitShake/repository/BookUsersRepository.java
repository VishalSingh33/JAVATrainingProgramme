package com.example.fruitShake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookUsersRepository extends JpaRepository<BookUsersRepository, String>{

}  
