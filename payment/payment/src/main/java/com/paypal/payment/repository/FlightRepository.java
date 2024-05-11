package com.paypal.payment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paypal.payment.entites.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

        @Query(nativeQuery = true, value = " SELECT * FROM flight WHERE DATE(origin_date_time) = :dateTime " +
                        " AND origin LIKE CONCAT('%', :origin, '%') " +
                        " AND destination LIKE CONCAT('%', :destination, '%') " +
                        " AND ( (:startTime IS NULL AND :endTime IS NULL) " +
                        " OR ( TIME(origin_date_time) >= :startTime " +
                        " AND TIME(origin_date_time) <= :endTime ) ) " )
        Page<Flight> findByOriginDateTimeBetween(String dateTime, String origin, String destination, String startTime,
                        String endTime, Pageable pageable);

}