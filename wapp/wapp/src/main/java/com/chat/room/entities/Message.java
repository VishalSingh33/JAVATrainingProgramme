package com.chat.room.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.chat.room.dto.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message implements Serializable {

  @Id
  @Column(name = "message_id")
  private String messageId;

  @Column(name = "sender_id", nullable = false)
  private String senderId;

  @Column(name = "receiver_id", nullable = false)
  private String receiverId;

  @Column(name = "content", nullable = false)
  private String message;

  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private MessageStatus status;
}
