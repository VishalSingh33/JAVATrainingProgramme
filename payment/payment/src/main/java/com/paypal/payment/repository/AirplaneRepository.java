package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.payment.entites.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String>{

}
