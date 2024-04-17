package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.Checkin;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, String>{
    
}
