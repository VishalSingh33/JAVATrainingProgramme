package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.Fare;


@Repository
public interface FareRepository extends JpaRepository<Fare, String>{
    
}
