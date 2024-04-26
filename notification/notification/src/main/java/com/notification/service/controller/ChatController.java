// package com.zyapaar.chatservice.controller;

// import javax.validation.Valid;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import com.zyapaar.chatservice.dto.ChatMessageResquestDto;
// import com.zyapaar.chatservice.service.ChatService;
// import com.zyapaar.commons.dto.Response;
// import com.zyapaar.commons.request.ListingRequest;
// import com.zyapaar.commons.utils.DateTimeUtils;
// import lombok.RequiredArgsConstructor;


// @Controller
// @RequiredArgsConstructor
// @CrossOrigin("*")
// public class ChatController {

//   private final ChatService chatService;

//   // @MessageMapping("/app")
//   public ResponseEntity<Response> processMessage(@Payload ChatMessageResquestDto chatMessage) {

//     chatService.newChatMessage(chatMessage);

//     return ResponseEntity.status(HttpStatus.CREATED)
//     .body(Response.builder().message("Page created")
//         .timestamp(DateTimeUtils.currentDateTimeUTCInString()).build());
//     // var chatId =
//     //     chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
//     // chatMessage.setChatId(chatId.get());

//     // ChatMessage saved = chatMessageService.save(chatMessage);
//     // messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
//     //     new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName()));
//   }

//   // @GetMapping("/messages/{senderId}/{receiverId}/count")
//   public ResponseEntity<Long> countNewMessages(@PathVariable String senderId,
//       @PathVariable String receiverId) {

//     return ResponseEntity.ok(chatService.countNewMessages(senderId, receiverId));
//   }

//   // @PostMapping("/messages/{senderId}/{receiverId}")
//   public ResponseEntity<Response> findChatMessages(@PathVariable String senderId,
//       @PathVariable String receiverId, @Valid @RequestBody ListingRequest request) {
//     return ResponseEntity.status(HttpStatus.OK)
//       .body(Response.builder().message("data found")
//       .data(chatService.findChatMessages(senderId, receiverId, request))
//       .timestamp(DateTimeUtils.currentDateTimeUTCInString())
//       .build());
//   }

  // @GetMapping("/messages/{id}")
  // public ResponseEntity<?> findMessage(@PathVariable String id) { 
  //   return ResponseEntity.ok(chatService.findById(id));
  // }

  // @PostMapping(value="/chat-room")
  // public ResponseEntity<Response>  getActiveChatRoom(
  //   @RequestHeader("Z-AUTH-USERID") String userId
  //   , @Valid @RequestBody ListingRequest request) {
  //     return chatService.getActiveChatRoom(userId, request);
  // }
  
  // @GetMapping("/messages/unread/count")
  // public ResponseEntity<Long> countUnreadMessage(
  //   @RequestHeader("Z-AUTH-USERID") String userId){
  //     return ResponseEntity.ok(chatService.getUnReadCount(userId));
  // }

//   // @GetMapping("/messages/status/{id}")
//   public ResponseEntity<Response> changeStatusForMessage(
//     @PathVariable String id, @RequestHeader("Z-AUTH-USERID") String userId
//   ){

//     chatService.changeStatusForMessage(id, userId);
//     return ResponseEntity.status(HttpStatus.OK)
//       .body(Response.builder().message("status changed")
//       .timestamp(DateTimeUtils.currentDateTimeUTCInString())
//     .build());
//   }


// }
