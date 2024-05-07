package com.airlines.british.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.Airplane;


@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String>{

    @Query(nativeQuery = true, value = " Select * from airplane where airplane_id = :airplaneId  ")
    Optional<List<Airplane>> airplaneById(String airplaneId);
    
}
