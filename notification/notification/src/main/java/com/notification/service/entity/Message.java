package com.notification.service.entity;

import java.time.OffsetDateTime;

import com.notification.service.dto.MessageStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  // @Column(name = "sender_id")
  private String sender_id;

  // @Column(name = "reciever_id")
  private String receiver_id;

  private String content;

  // @Column(name = "created_on")
  private OffsetDateTime created_on;

  // @Column(name = "sender_id")
  private MessageStatus status;
}
