package com.chat.room.entities;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inquire_room")
public class InquireRoom {

  @Id
  private String id; //inquireRoomId

  @Column(name = "sender_id")
  private String senderId;

  @Column(name = "receiver_id")
  private String receiverId;

  @Column(name = "created_on")
  private OffsetDateTime createdOn;

  @Column(name = "updated_on")
  private OffsetDateTime updatedOn;
  
}
