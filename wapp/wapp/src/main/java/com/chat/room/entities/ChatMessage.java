package com.chat.room.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
// import org.hibernate.annotations.TypeDef;
// import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_message")
// @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ChatMessage {

    @Id
    @Column(name = "message_id", nullable = false, unique = true)
    private String messageId;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "message")
    private Message message;

    @Column
    private LocalDateTime createdAt;
    
    

}