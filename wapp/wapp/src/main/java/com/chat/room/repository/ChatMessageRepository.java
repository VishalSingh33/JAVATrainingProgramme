package com.chat.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.room.entities.ChatMessage;
import com.chat.room.entities.Message;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    
    List<Message> findByChatId(String chatId);
}