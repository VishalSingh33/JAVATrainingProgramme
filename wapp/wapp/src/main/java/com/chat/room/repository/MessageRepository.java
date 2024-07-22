package com.chat.room.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.room.entities.InquireMessage;
import com.chat.room.entities.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, String>{
    
}
