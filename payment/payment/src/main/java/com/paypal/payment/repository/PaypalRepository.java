package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paypal.payment.entites.PayPalPayment;

@Repository
public interface PaypalRepository extends JpaRepository<PayPalPayment, String>{

}
