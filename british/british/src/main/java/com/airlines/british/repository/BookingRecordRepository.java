package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlines.british.entites.BookingRecord;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, String>{
    
}
