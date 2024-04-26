package com.notification.service.service;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

import com.notification.service.b2bProperties.ListingRequest;
import com.notification.service.b2bProperties.ListingResponse;
import com.notification.service.dto.InquireMessageDto;
import com.notification.service.dto.InquireResponseDto;
import com.notification.service.dto.OriginType;
import com.notification.service.entity.InquireMessage;

public interface ChatInquireService {

  void newInquire(InquireMessageDto inquire);

  ResponseEntity<?> inquireChatRoom(String userId, ListingRequest request);

  ListingResponse findInquireMessages(String senderId, String receiverId,
      @Valid ListingRequest request);

  Long countNewInquire(String senderId, String receiverId);

  Long getUnReadCount(String userId);

  InquireResponseDto findById(String id);

  void changeStatusForSingleMessage(String inquireId, String userId);

  // ResponseEntity<InquireMessage> getDetailsByOrigin(String originId, String userId, OriginType type);

}
