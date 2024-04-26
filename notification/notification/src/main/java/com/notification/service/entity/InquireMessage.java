package com.notification.service.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inquire_message")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class InquireMessage {

  @Id
  private String id;

  @Column(name = "inquire_id")
  private String inquireId;

  // @Type(type = "jsonb")
  // @Column(name = "content", columnDefinition = "jsonb")
  @Column(name = "content",columnDefinition = "TEXT")
  private Content content;

  @Column(name = "sender_id")
  private String senderId;

  @Column(name = "receiver_id")
  private String receiverId;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "status")
  private String status;

  @Column(name = "created_on")
  private OffsetDateTime createdOn;

  @Column(name = "updated_on")
  private OffsetDateTime updatedOn;

  @Column(name = "is_update")
  private Boolean isUpdate;

  
}
