package com.notification.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.notification.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

  
}