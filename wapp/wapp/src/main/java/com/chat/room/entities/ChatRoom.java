package com.chat.room.entities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoom {
  
  @Id
  private String id; //chatId

  @Column(name = "sender_id")
  private String senderId;

  @Column(name = "receiver_id")
  private String receiverId;

  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @Column(name = "updated_on")
  private LocalDateTime updatedOn;
}
