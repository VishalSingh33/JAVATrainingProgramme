package com.chat.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireNotification {

  private String id;
  private String senderId;
  private String senderName;
  
}
