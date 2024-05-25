package com.chat.room.dto;

import lombok.Data;

@Data
public class ChatMessageResquestDto {
  
  private String senderId;
  private String receiverId;
  private String message;
}
