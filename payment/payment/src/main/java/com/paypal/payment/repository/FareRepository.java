package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paypal.payment.entites.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, String>{
    
}
