package com.notification.service.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notification.service.b2bProperties.ListingRequest;
import com.notification.service.dto.OriginType;
import com.notification.service.service.ChatInquireService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatInquireController {

  private final ChatInquireService chatInquireService;

  // @PostMapping(value="/inquire/room")
  @PostMapping(value = "/chat-room")
  public ResponseEntity<?> inquireChatRoom(
      @RequestHeader("Z-AUTH-USERID") String userId, @Valid @RequestBody ListingRequest request) {

    return chatInquireService.inquireChatRoom(userId, request);
  }

  // @PostMapping("/inquire/{senderId}/{receiverId}")
  @PostMapping("/messages/{senderId}/{receiverId}")
  public ResponseEntity<?> findInquireMessages(@PathVariable String senderId,
      @PathVariable String receiverId, @Valid @RequestBody ListingRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    // return ResponseEntity.status(HttpStatus.OK)
    //     .body(Response.builder().message("data found")
    //         .data(chatInquireService.findInquireMessages(senderId, receiverId, request))
    //         .timestamp(DateTimeUtils.currentDateTimeUTCInString())
    //         .build());
  }

  // @GetMapping("/inquire/{senderId}/{receiverId}/count")
  @GetMapping("/messages/{senderId}/{receiverId}/count")
  public ResponseEntity<Long> countNewInquire(@PathVariable String senderId,
      @PathVariable String receiverId) {

    return ResponseEntity.ok(chatInquireService.countNewInquire(senderId, receiverId));
  }

  // @GetMapping("/inquire/unread/count")
  @GetMapping("/messages/unread/count")
  public ResponseEntity<Long> countUnreadMessage(
      @RequestHeader("Z-AUTH-USERID") String userId) {
    return ResponseEntity.ok(chatInquireService.getUnReadCount(userId));
  }

  // @GetMapping("/inquire/{id}")
  @GetMapping("/messages/{id}")
  public ResponseEntity<?> findInquire(@PathVariable String id) {

    return ResponseEntity.ok(chatInquireService.findById(id));
  }

  // @GetMapping("/inquire/status/{id}")
  @GetMapping("/messages/status/{id}")
  public ResponseEntity<?> changeStatusForSingleMessage(
      @PathVariable String id, @RequestHeader("Z-AUTH-USERID") String userId) {

    chatInquireService.changeStatusForSingleMessage(id, userId);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
    // return ResponseEntity.status(HttpStatus.OK)
    // .body(Response.builder().message("status changed")
    // .timestamp(DateTimeUtils.currentDateTimeUTCInString())
    // .build());
  }

  // // @GetMapping("/inquire/origin/{type}/{id}")
  // @GetMapping("/messages/origin/{type}/{id}")
  // public ResponseEntity<?> getDetailsByOrigin(@PathVariable("type") OriginType type,
  //     @PathVariable("id") String originId, @RequestHeader("Z-AUTH-USERID") String userId) {

  //   return chatInquireService.getDetailsByOrigin(originId, userId, type);

  // }

}
