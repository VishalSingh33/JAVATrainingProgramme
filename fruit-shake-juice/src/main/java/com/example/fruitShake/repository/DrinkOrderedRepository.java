package com.example.fruitShake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fruitShake.entities.BookUsers;
import com.example.fruitShake.entities.DrinkOrdered;


@Repository
public interface DrinkOrderedRepository extends JpaRepository<DrinkOrdered, String>{

}  
