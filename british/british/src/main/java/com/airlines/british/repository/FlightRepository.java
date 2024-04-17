package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlines.british.entites.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, String>{
    
}
