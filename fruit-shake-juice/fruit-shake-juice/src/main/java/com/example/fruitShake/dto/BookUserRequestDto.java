package com.example.fruitShake.dto;

import java.time.OffsetDateTime;
import com.example.fruitShake.entities.Books;
import lombok.Data;

/**
 * connection request listing dao
 * 
 */
@Data
public class BookUserRequestDto {

  private String id;
  private String fromUserId;
  private String toUserId;
  private String status;
  private String message;
  private String ageOfRequest;
  private OffsetDateTime createdOn;
  private OffsetDateTime updatedOn;
  private String userProfile;
  private String userName;
  private String userDesignation;

}
