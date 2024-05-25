package com.chat.room.controller;

import lombok.AllArgsConstructor;
import java.util.List;

import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.chat.room.dto.ChatMessageResquestDto;
import com.chat.room.dto.MessageDto;
import com.chat.room.dto.ChatNotification;
import com.chat.room.entities.Message;
import com.chat.room.service.ChatMessageService;

@RestController
@AllArgsConstructor
public class ChatController {

    // private SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    @SendTo("/user/chatMessage")
    public ResponseEntity<?> processMessage(@Payload ChatMessageResquestDto chatMessage) {

        return chatMessageService.newProcess(chatMessage);
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> findChatMessages(@PathVariable String senderId,
            @PathVariable String receiverId) {

        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
    }

}