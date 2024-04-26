package com.notification.service.dto;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class ChatMessageResponseDto {
  
  private String id;
  private String chatId;
  private String senderId;
  private String receiverId;
  private String content;
  private MessageStatus status;
  private OffsetDateTime createdOn;
}
