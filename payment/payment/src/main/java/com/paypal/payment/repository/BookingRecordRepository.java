package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.payment.entites.BookingRecord;


@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, String>{
    
}
