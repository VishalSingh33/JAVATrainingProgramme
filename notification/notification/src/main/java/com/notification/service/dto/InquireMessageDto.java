package com.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquireMessageDto {

  private String senderId;
  private String receiverId; //postOwnerId or receiverId or null in case of product
  private String originId; //feedId
  private OriginType originType;
  private String message;
  // private String url;
}
