package com.notification.service.dto;

import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public interface ChatRoomDto {

  String getChatRoomId();
  String getId();
  String getFullName();
  String getProfileImg();
  String getProfileTitle();
  Timestamp getUpdatedOn();
  Boolean getIsConnected();
  
}