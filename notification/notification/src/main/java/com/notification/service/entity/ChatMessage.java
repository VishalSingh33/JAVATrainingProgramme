package com.notification.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_message")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ChatMessage {

  @Id
  private String id;

  @Column(name = "chat_id")
  private String chatId;

  // @Type(type = "jsonb")
  // @Column(name = "message", columnDefinition = "jsonb")
  @Column(name = "message",columnDefinition = "TEXT")
  private Message message;
}
