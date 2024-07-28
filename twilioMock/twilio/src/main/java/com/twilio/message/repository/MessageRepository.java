package com.twilio.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twilio.message.entities.MessageBox;

@Repository
public interface MessageRepository extends JpaRepository<MessageBox, String>{

}