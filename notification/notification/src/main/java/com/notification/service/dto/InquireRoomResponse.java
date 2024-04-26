package com.notification.service.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquireRoomResponse {
  
  private String chatRoomId;
  private String id; //userId
  private String fullName;
  private String profileImg;
  private String profileTitle;
  private Timestamp updateOn; //not updatedOn as mistake in old dto
  private InquireResponseDto message;
  private Long count;

}
