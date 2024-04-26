package com.notification.service.dto;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class InquireResponseDto {
  
  private String id;
  private String inquireId;
  private String senderId;
  private String receiverId;
  private String message;
  private String status;
  private OffsetDateTime createdOn;
  private String originId;
  private String origin;
  // private String url;
  private String updatedOn;
  
}
