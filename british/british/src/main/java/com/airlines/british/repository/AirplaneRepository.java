package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String>{

}
