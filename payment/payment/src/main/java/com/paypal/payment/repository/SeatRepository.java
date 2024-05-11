package com.paypal.payment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.paypal.payment.entites.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {

    @Query(nativeQuery = true, value = " Select * from seat where airplane_id = :airplaneId  ")
    List<Seat> seatByAirplane(String airplaneId);

    @Query(nativeQuery = true, value = " Select seat_number from seat where airplane_id = :airplaneId AND features = false  ")
    List<String> seatByIdList(@Param("airplaneId") String airplaneId);

}
