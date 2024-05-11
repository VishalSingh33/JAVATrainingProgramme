package com.paypal.payment.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.paypal.payment.entites.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

    // SELECT COUNT(*) FROM Passenger WHERE user_id = :userId
    //          AND DATE(created) = :today GROUP BY user_id HAVING COUNT(*) > 3

    @Query(nativeQuery = true, value = " SELECT COUNT(*) FROM Passenger WHERE user_id = :userId  " +
            " AND DATE(created) = :today ")
    long countByUserId(@Param("userId") String userId, @Param("today") LocalDate today);

}
