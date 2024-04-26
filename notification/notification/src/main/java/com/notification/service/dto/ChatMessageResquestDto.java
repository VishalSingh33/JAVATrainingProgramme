package com.notification.service.dto;

import lombok.Data;

@Data
public class ChatMessageResquestDto {
  
  private String senderId;
  private String receiverId;
  private String content;
}
