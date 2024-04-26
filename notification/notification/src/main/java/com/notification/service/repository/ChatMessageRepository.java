package com.notification.service.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.notification.service.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

  @Query(nativeQuery = true,
      value = "SELECT * FROM chat_message cm " + 
      "WHERE cm.chat_id = :chatId " +
      "ORDER BY cm.message ->> 'created_on' DESC")
  List<ChatMessage> findByChatId(String chatId, Pageable requestPage);

  @Query(nativeQuery = true,  
    value ="SELECT COUNT(cm.id) FROM chat_message cm " + 
    "WHERE cm.message ->> 'sender_id' = :senderId " + 
    "AND cm.message ->> 'receiver_id' = :receiverId " + 
    "AND cm.message ->> 'status' = 'RECEIVED'")
  Long countReceivedMessageForSenderAndReceiver(String senderId, String receiverId);

  // List<ChatMessage> findByChatId(String chatId);

  // @Query(nativeQuery = true,
  // value = "select * from chat_message cm where cm.message ->>'sender_id' = :sender " +
  // "and cm.message ->> 'receiver_id' = :receiver " +
  // "and cm.message ->> 'status' = status")
  // List<ChatMessage> findUsingSenderAndReceiver(String sender,
  // String receiver, MessageStatus status);

  @Query(nativeQuery = true,
    value= "SELECT COUNT(cm.id) FROM chat_message cm " + 
    "WHERE cm.message ->> 'receiver_id' = :userId " + 
    "AND cm.message ->> 'status' = 'RECEIVED' ")
  Long getUnReadCount(String userId);

}
