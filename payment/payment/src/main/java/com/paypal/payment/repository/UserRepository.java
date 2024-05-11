package com.paypal.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paypal.payment.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    
}
