package com.notification.service.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.notification.service.dto.BlockOrigin;
import com.notification.service.dto.BlockedStatus;
import com.notification.service.dto.ChatMessageResponseDto;
import com.notification.service.dto.ChatMessageResquestDto;
import com.notification.service.dto.ChatRoomDto;
import com.notification.service.entity.ChatMessage;
import com.notification.service.entity.ChatNotification;
import com.notification.service.entity.ChatRoom;
import com.notification.service.entity.Message;
import com.notification.service.dto.MessageStatus;
import com.notification.service.entity.User;
import com.notification.service.mapper.MessageMapper;
import com.notification.service.b2bProperties.*;
// import com.notification.service.repository.BlockUserRepository;
import com.notification.service.repository.ChatMessageRepository;
import com.notification.service.repository.ChatRoomRepository;
// import com.notification.service.repository.UserConnectionRepository;
import com.notification.service.repository.UserRepository;
import com.notification.service.exceptionHandler.BadRequestException;
// import com.notification.commons.dto.Response;
// import com.notification.commons.request.ListingRequest;
// import com.notification.commons.response.ListingResponse;
// import com.notification.commons.utils.DateTimeUtils;
// import com.notification.commons.utils.SequenceGenerator;
// import com.notification.exceptionhandler.custom.BadRequestException;
// import com.notification.exceptionhandler.custom.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManageChatService implements ChatService {

  private final ChatRoomRepository chatRoomRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final SimpMessagingTemplate messagingTemplate;
  private final UserRepository userRepository;
  private final B2bProperties b2bProperties;
  private final MessageMapper messageMapper;
  // private final BlockUserRepository blockUserRepository;
  // private final UserConnectionRepository userConnectionRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void newChatMessage(ChatMessageResquestDto chatMessage) {

    log.info("[newChatMessage] new message: from: {}, to: {}",
       chatMessage.getSenderId(), chatMessage.getReceiverId());

    OffsetDateTime offsetDateTime = new Date().toInstant().atOffset(ZoneOffset.UTC);
    String chatId = findOrCreateChatRoom(chatMessage.getSenderId(), chatMessage.getReceiverId());

    // checkIsConnected(chatMessage.getSenderId(), chatMessage.getReceiverId());

    String messageId = UUID.randomUUID().toString();

    Message message = new Message();
    message = messageMapper.update(chatMessage, offsetDateTime, MessageStatus.RECEIVED);
    // message.setContent(chatMessage.getContent());
    // message.setSender_id(chatMessage.getSenderId());
    // message.setReceiver_id(chatMessage.getReceiverId());
    // message.setCreated_on(createdOn);
    // message.setStatus(MessageStatus.RECEIVED);

    ChatMessage chat = new ChatMessage(messageId, chatId, message);

    User sender = userRepository.findById(chatMessage.getSenderId())
        .orElseThrow(() -> new RuntimeException("User not found with id: " + chatMessage.getSenderId()));

    userRepository.findById(chatMessage.getReceiverId())
        .orElseThrow(() -> new RuntimeException("User not found with id: " + chatMessage.getReceiverId()));

    messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages",
        new ChatNotification(messageId, sender.getId(), sender.getFullName()));


    // sending to receiver's channel while receiver connecting they will subscribe to receiver's
    ChatRoom chatRoom = chatRoomRepository.findById(chatId)
    .orElseThrow(() -> new RuntimeException("User not found with id: " + chatId));

    chatRoom.setUpdatedOn(offsetDateTime);

    try {
      chatRoomRepository.save(chatRoom);
      chatMessageRepository.save(chat);
      
    } catch (Exception e) {
      log.info("[newChatMessage] error while saving data: {}", e);
      throw new BadRequestException("Something went wrong");
    }
    

  }

  @Override
  public Long countNewMessages(String senderId, String receiverId) {

    log.info("[countNewMessages] count receivedMessage");
    return chatMessageRepository.countReceivedMessageForSenderAndReceiver(senderId, receiverId);

  }

  @Override
  public ListingResponse findChatMessages(String senderId, String recieverId,
      ListingRequest request) {

    log.info("[findChatMessages] senderId: {}, reciveverId: {}", senderId, recieverId);
    String chatId = findOrCreateChatRoom(senderId, recieverId);

    Pageable requestedPage =
        PageRequest.of(request.getPage(), b2bProperties.getPaging().getMessageListSize());
    
    List<ChatMessage> messages = chatMessageRepository.findByChatId(chatId, requestedPage);
    // var messages = chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
    
    List<ChatMessageResponseDto> messageDto = new ArrayList<>();

    if (!ObjectUtils.isEmpty(messages)) {
      updateStatus(messages, MessageStatus.DELIVERED);

      messageDto = messageMapper.toMessageDtoList(messages);
    }

    ListingResponse response = new ListingResponse();
    response.setContent(messageDto);
    response.setPage(request.getPage());
    return response;
  }

  @Override
  public ChatMessageResponseDto findById(String id) {

    log.info("[findById] id: {}", id);
    ChatMessage message=  chatMessageRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
      
    // if(blockUserRepository.isBlockedUser(message.getMessage().getSender_id(), 
    //   message.getMessage().getReceiver_id(), 
    //   BlockedStatus.BLOCKED.status(), BlockOrigin.USER.origin())){

    //   log.info("[findById] fromUserId: {}, toUserId: {} is blocked", 
    //       message.getMessage().getSender_id(), message.getMessage().getReceiver_id());
    //   throw new BadRequestException("User profile is blocked for you"); 
    // }

    return messageMapper.toMessageDto(message);
    // return repository.findById(id).map(chatMessage -> {
    // chatMessage.setStatus(MessageStatus.DELIVERED);
    // return repository.save(chatMessage);
    // })() -> new ResourceNotFoundException("can't find message (" + id + ")")
  }

  public String findOrCreateChatRoom(String senderId, String receiverId) {

    log.info("[findOrCreateChatRoom] sender: {}, receiver: {}", senderId, receiverId);

    if(senderId.equals(receiverId)){
      throw new BadRequestException("You cannot chat with yourself");
    }
    // if(blockUserRepository.isBlockedUser(receiverId, senderId, 
    //     BlockedStatus.BLOCKED.status(), BlockOrigin.USER.origin())){

    //   log.info("[findOrCreateChatRoom] fromUserId: {}, toUserId: {} is blocked"
    //     , senderId, receiverId);
    //   throw new BadRequestException("User profile is blocked for you"); 
    // }
    OffsetDateTime createdOn = new Date().toInstant().atOffset(ZoneOffset.UTC);

    String chatId = chatRoomRepository.findByIds(senderId, receiverId);
    if (!StringUtils.hasText(chatId)) {
      // createNew chatRoom
      //if they are connected then only create new chatRoom
      // checkIsConnected(senderId, receiverId);
      chatId = UUID.randomUUID().toString();

      ChatRoom chatRoom = new ChatRoom(chatId, senderId, receiverId, createdOn, createdOn);
      log.info("[findOrCreateChatRoom] created new chatRoom: {}",chatRoom.getId());
      chatRoomRepository.save(chatRoom);
    }

    return chatId;
  }

  // private void checkIsConnected(String senderId, String receiverId) {
  //   if(!userConnectionRepository.checkWhetherConnected(senderId, receiverId)){

  //     log.info("[findOrCreateChatRoom] senderId: {}, receiverId: {} are not connected"
  //       ,senderId, receiverId);
  //     throw new BadRequestException("Users are not connected");
  //   }
  // }

  public void updateStatus(List<ChatMessage> messages, MessageStatus status) {

    messages.forEach(message -> {
      log.info("[updateStatus] messageId: {}", message.getId());
      changeMessage(message, status);
      // message.getMessage().setStatus(status);
    });
    chatMessageRepository.saveAll(messages);
    // Query query =
    // new Query(Criteria.where("senderId").is(senderId).and("receiverId").is(receiverId));
    // Update update = Update.update("status", status);
    // mongoOperations.updateMulti(query, update, ChatMessage.class);
  }

  private void changeMessage(ChatMessage message, MessageStatus status) {
    message.getMessage().setStatus(status);
  }

  @Override
  public ResponseEntity<?> getActiveChatRoom(String userId, ListingRequest request) {

    log.info("[getActiveChatRoom] getChatRoom of userId: {}", userId);
    Pageable requestedPage =
        PageRequest.of(request.getPage(), b2bProperties.getPaging().getChatRoomListSize());
    List<ChatRoomDto> activeChatRoom = chatRoomRepository.getActiveChatRoom(userId,
      BlockedStatus.BLOCKED.status(), BlockOrigin.USER.origin(), requestedPage);

    ListingResponse response = new ListingResponse();
    response.setContent(activeChatRoom);
    response.setPage(request.getPage());

    return ResponseEntity.status(HttpStatus.CREATED).body(response);

    // return ResponseEntity.status(HttpStatus.OK)
    //   .body(Response.builder().message("data found")
    //   .data(response)
    //   .timestamp(DateTimeUtils.currentDateTimeUTCInString())
    //   .build());
  }

  @Override
  public Long getUnReadCount(String userId){

    log.info("[getUnReadCount] userId: {}", userId);
    return chatMessageRepository.getUnReadCount(userId);
  }

  @Override
  public void changeStatusForMessage(String messageId, String userId){

    ChatMessage message =  chatMessageRepository.findById(messageId)
         .orElseThrow(() -> new RuntimeException("User not found with id: " + messageId));


    if(!message.getMessage().getReceiver_id().equals(userId)){

      log.info("[changeStatusForMessage] message's receiverId {} != userId {}",
         message.getMessage().getReceiver_id(),userId);
      throw new BadRequestException("You are not eligible to change status of this message");
    }
    changeMessage(message, MessageStatus.DELIVERED);
  }

}
