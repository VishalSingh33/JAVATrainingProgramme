package com.notification.service.service;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

import com.notification.service.b2bProperties.ListingRequest;
import com.notification.service.b2bProperties.ListingResponse;
import com.notification.service.dto.ChatMessageResponseDto;
import com.notification.service.dto.ChatMessageResquestDto;
// import com.zyapaar.commons.response.ListingResponse;

public interface ChatService {

  void newChatMessage(ChatMessageResquestDto chatMessage);

  Long countNewMessages(String senderId, String receiverId);

  ChatMessageResponseDto findById(String id);

  ListingResponse findChatMessages(String senderId, String receiverId,
      @Valid ListingRequest request);

  ResponseEntity<?> getActiveChatRoom(String userId, @Valid ListingRequest request);

  Long getUnReadCount(String userId);

  void changeStatusForMessage(String messageId, String userId);

}
