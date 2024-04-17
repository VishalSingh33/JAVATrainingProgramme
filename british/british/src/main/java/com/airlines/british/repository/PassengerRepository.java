package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlines.british.entites.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String>{
    
}
