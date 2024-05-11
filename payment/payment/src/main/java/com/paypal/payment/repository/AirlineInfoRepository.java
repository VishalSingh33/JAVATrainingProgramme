package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.payment.entites.AirlineInfo;



@Repository
public interface AirlineInfoRepository extends JpaRepository<AirlineInfo, String> {
}
