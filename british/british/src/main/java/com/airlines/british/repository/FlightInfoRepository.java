package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlines.british.entites.FlightInfo;


@Repository
public interface FlightInfoRepository extends JpaRepository<FlightInfo, String>{
    
}
