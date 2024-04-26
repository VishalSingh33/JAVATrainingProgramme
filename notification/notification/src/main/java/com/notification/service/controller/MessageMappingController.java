package com.notification.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.notification.service.service.ChatInquireService;
import com.notification.service.b2bProperties.DateTimeUtils;
import com.notification.service.dto.InquireMessageDto;
import com.notification.service.entity.InquireMessage;

// import com.notification.service.dto.Response;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageMappingController {
  private final ChatInquireService chatInquireService;

  // @MessageMapping("/chat")
  @MessageMapping("/app")
  public ResponseEntity<?> processInquire(@Payload InquireMessageDto chatMessage) {

    chatInquireService.newInquire(chatMessage);

    return ResponseEntity.status(HttpStatus.CREATED).body(null);

    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(Responce.builder().message("Inquire Created")
    //     .timestamp(DateTimeUtils.currentDateTimeUTCInString()).build());
  }
  
}
