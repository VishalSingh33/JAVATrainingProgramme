package com.chat.room.dto;

import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public interface InquireRoomDto {

  String getChatRoomId();
  String getId(); //userId
  String getFullName();
  String getProfileImg();
  String getProfileTitle();
  Timestamp getUpdatedOn();
  String getMyId();
  
}
