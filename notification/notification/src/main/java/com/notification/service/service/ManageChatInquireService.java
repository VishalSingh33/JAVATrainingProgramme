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
import com.notification.service.dto.FeedDto;
import com.notification.service.dto.InquireMessageDto;
import com.notification.service.dto.InquireResponseDto;
import com.notification.service.dto.InquireRoomDto;
import com.notification.service.dto.InquireRoomResponse;
import com.notification.service.dto.OriginType;
import com.notification.service.dto.ProductDto;
// import com.notification.service.entity.EntityProduct;
// import com.notification.service.entity.Feed;
import com.notification.service.entity.InquireMessage;
import com.notification.service.entity.InquireNotification;
import com.notification.service.entity.InquireRoom;
import com.notification.service.entity.Content;
import com.notification.service.dto.InquireStatus;
import com.notification.service.entity.User;
import com.notification.service.exceptionHandler.BadRequestException;
import com.notification.service.mapper.InquireMapper;
import com.notification.service.b2bProperties.*;
// import com.notification.service.repository.BlockUserRepository;
// import com.notification.service.repository.EntityProductRepository;
// import com.notification.service.repository.FeedRepository;
import com.notification.service.repository.InquireMessageRepository;
import com.notification.service.repository.InquireRoomRepository;
import com.notification.service.repository.UserRepository;
// import com.zyapaar.commons.dto.Response;
// import com.zyapaar.commons.request.ListingRequest;
// import com.zyapaar.commons.response.ListingResponse;
// import com.zyapaar.commons.utils.DateTimeUtils;
// import com.zyapaar.commons.utils.SequenceGenerator;
// import com.zyapaar.exceptionhandler.custom.BadRequestException;
// import com.zyapaar.exceptionhandler.custom.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageChatInquireService implements ChatInquireService{

  private final InquireRoomRepository inquireRoomRepository;
  private final InquireMessageRepository inquireMessageRepository;
  // private final BlockUserRepository blockUserRepository;
  private final InquireMapper inquireMapper;
  private final UserRepository userRepository;
  private final SimpMessagingTemplate messagingTemplate;
  private final B2bProperties b2bProperties;
  // private final EntityProductRepository entityProductRepostitory;
  // private final FeedRepository feedRepository;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void newInquire(InquireMessageDto newInquire) {

    log.info("[newInquire] new message: from: {}, to: {}",
       newInquire.getSenderId(), newInquire.getReceiverId());

    // validateUser(newInquire.getReceiverId());
    // User sender = validateUser(newInquire.getSenderId());
    // if(!ObjectUtils.isEmpty(newInquire.getOriginType())){
    //   validateOriginId(newInquire.getOriginType(), newInquire.getOriginId());

    //   // if(newInquire.getOriginType().equals(OriginType.PRODUCT)){
    //     // initially superAdmin details was not send in getProductById & getProductsByCompanyId.

    //   //   newInquire.setReceiverId(entityProductRepostitory.getSuperAdmin(newInquire.getOriginId()));
    //   // }
    // }
    User sender = new User(); // add by myself
    if(!StringUtils.hasText(newInquire.getReceiverId())){

      log.info("[newInquire] Wrong receiverId: {}",newInquire.getReceiverId());
      throw new BadRequestException("Enter valid receiverId");
    }
    OffsetDateTime offsetDateTime = new Date().toInstant().atOffset(ZoneOffset.UTC);
    
    InquireRoom inquireRoom = findOrCreateInquireRoom(newInquire.getSenderId(), 
      newInquire.getReceiverId(), newInquire.getOriginType());

    String messageId = UUID.randomUUID().toString();

    Content content = new Content();
    content = inquireMapper.createContent(newInquire);

    InquireMessage inquire = new InquireMessage(messageId, inquireRoom.getId(), 
        content, newInquire.getSenderId(), newInquire.getReceiverId(), true, 
        InquireStatus.RECEIVED.status(), offsetDateTime, offsetDateTime, false);
    
    // sending to receiver's channel while receiver connecting they will subscribe to receiver's
    messagingTemplate.convertAndSendToUser(newInquire.getReceiverId(), "/queue/messages",
        new InquireNotification(messageId, sender.getId(), sender.getFullName()));

    inquireRoom.setUpdatedOn(offsetDateTime);

    try {
      inquireRoomRepository.save(inquireRoom);
      inquireMessageRepository.save(inquire);
      
    } catch (Exception e) {
      log.info("[newInquire] error while saving data: {}", e);
      throw new BadRequestException("Something went wrong");
    }
  }
    
    public InquireRoom findOrCreateInquireRoom(String senderId, String receiverId, OriginType origin){

      // if(senderId.equals(receiverId)){
      //   throw new BadRequestException("You cannot chat with yourself");
      //   //You cannot inquire your own product
      //   //You cannot inquire for your own feed
      // }

      // validateBlocked(receiverId, senderId, origin);
      
      // InquireRoom inquireRoom = inquireRoomRepository.findByIds(senderId, receiverId);
      InquireRoom inquireRoom = findInquireRoom(senderId, receiverId, origin);
      if (ObjectUtils.isEmpty(inquireRoom)) {
        // createNew chatRoom
        String inquireRoomId = UUID.randomUUID().toString();
        OffsetDateTime createdOn = new Date().toInstant().atOffset(ZoneOffset.UTC);
  
        inquireRoom = new InquireRoom(inquireRoomId, senderId, receiverId, createdOn, createdOn);
        log.info("[findOrCreateInquireRoom] created new chatRoom: {}",inquireRoom.getId());
        inquireRoomRepository.save(inquireRoom);
      }

      return inquireRoom;

    }

    public InquireRoom findInquireRoom(String senderId, String receiverId, OriginType origin){

      if(senderId.equals(receiverId)){
        throw new BadRequestException("You cannot chat with yourself");
        //You cannot inquire your own product
        //You cannot inquire for your own feed
      }

      // validateBlocked(receiverId, senderId, origin);
      InquireRoom inquireRoom = inquireRoomRepository.findByIds(senderId, receiverId);

      return inquireRoom;
    }

    // private void validateBlocked(String receiverId, String senderId, OriginType origin){

    //   if(blockUserRepository.isBlockedUser(receiverId, senderId, 
    //     BlockedStatus.BLOCKED.status(), BlockOrigin.USER.origin())){

    //     if(ObjectUtils.isEmpty(origin)){
    //       log.info("[validateBlocked] fromUserId: {}, toUserId: {} is blocked", 
    //       senderId, receiverId);
    //       throw new BadRequestException("User profile is blocked for you"); 
    //     }

    //     if(OriginType.PRODUCT.equals(origin)){
    //       log.info("[validateBlocked] fromUserId: {}, toUserId: {} is blocked", 
    //         senderId, receiverId);
    //       throw new BadRequestException("Company admin's profile is blocked for you"); 

    //       //Here we can do some changes like creating chat_room with Zyapaar admin or something
    //     }
    //   }
    // }

    @Override
    public ResponseEntity<?> inquireChatRoom(String userId, ListingRequest request){

      log.info("[inquireChatRoom] inquireChatRoom of userId: {}", userId);
      Pageable requestPage =
          PageRequest.of(request.getPage(), b2bProperties.getPaging().getChatRoomListSize());
      List<InquireRoomDto> inquireRoom = inquireRoomRepository.inquireChatRoom(userId, 
          BlockedStatus.BLOCKED.status(), BlockOrigin.USER.origin(), requestPage);

      List<InquireRoomResponse> responseMessage = inquireMapper.toInquireRoomResponse(inquireRoom);

      // // ListingResponse response = new ListingResponse();
      // // response.setContent(responseMessage);
      // // response.setPage(request.getPage());
      
      // return ResponseEntity.status(HttpStatus.OK)
      // .body(Response.builder().message("data found")
      // .data(new ListingResponse(responseMessage, request.getPage()))
      // .timestamp(DateTimeUtils.currentDateTimeUTCInString())
      // .build());
      return ResponseEntity.status(HttpStatus.OK)
      .body(new ListingResponse(responseMessage, request.getPage()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ListingResponse findInquireMessages(String senderId, String receiverId, ListingRequest request){

      List<InquireResponseDto> inquireMessageList = new ArrayList<>();
      InquireRoom inquireRoom = findInquireRoom(senderId, receiverId, null);
      if(!ObjectUtils.isEmpty(inquireRoom)){

        Pageable requestedPage =
          PageRequest.of(request.getPage(), b2bProperties.getPaging().getMessageListSize());

        // List<IInquireResponseDto> inquireMessage = inquireMessageRepository
        //     .findByInquireId(inquireRoom.getId(), requestedPage);

        List<InquireMessage> message =
            inquireMessageRepository.findByInquireId(inquireRoom.getId(), requestedPage);
        // List<InquireMessageDto> messageDto = new ArrayList<>();

        if(!ObjectUtils.isEmpty(message)){

          updateStatus(senderId, receiverId, InquireStatus.DELIVERED.status());
          // inquireMessageRepository.updateStatus(senderId, receiverId);
          inquireMessageList = inquireMapper.toInquireMessage(message);     
        }
      }
      return new ListingResponse(inquireMessageList, request.getPage());
    }

    private void updateStatus(String senderId, String receiverId,String status){

      List<InquireMessage> messages = inquireMessageRepository.findAllUnReadMessages(senderId, receiverId);
      if(!ObjectUtils.isEmpty(messages)){

        messages.forEach(message -> {
          changeMessageStatus(message, status);
        });
        inquireMessageRepository.saveAll(messages);
      }
    }

    private void changeMessageStatus(InquireMessage message, String status){
      message.setStatus(status);
    }
  
  @Override
  public Long countNewInquire(String senderId, String receiverId) {

    log.info("[countNewInquire] count receivedMessage");
    return inquireMessageRepository.countReceivedMessageForSenderAndReceiver(senderId, receiverId);

  }

  @Override
  public Long getUnReadCount(String userId){

    log.info("[getUnReadCount] userId: {}", userId);
    return inquireMessageRepository.getUnReadCount(userId);
  }

  @Override
  public InquireResponseDto findById(String id) {

    log.info("[findById] id: {}", id);
    InquireMessage message = validateInquireMessage(id);

    if(!message.getIsActive()){
      throw new BadRequestException("This message was deleted");
    }
    // validateBlocked(message.getSenderId(), message.getReceiverId(), null);
    InquireResponseDto messageDto = inquireMapper.toInquireMessage(message);

    return messageDto;
  }

  @Override
  public void changeStatusForSingleMessage(String inquireId, String userId){

    log.info("[changeStatusForSingleMessage] inquireId: {}", inquireId);

    InquireMessage inquire = validateInquireMessage(inquireId);

    if(!inquire.getReceiverId().equals(userId)){
      log.info("[changeStatusForMessage] message's receiverId {} != userId {}",
        inquire.getReceiverId(),userId);
      throw new BadRequestException("You are not eligible to change status of this message");
    }
    changeMessageStatus(inquire, InquireStatus.DELIVERED.status());
    inquireMessageRepository.save(inquire);
  }

  private InquireMessage validateInquireMessage(String id){
    return inquireMessageRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    // .orElseThrow(() -> new ResourceNotFoundException("inquire", "id", id));
  }

  // @Override
  // public ResponseEntity<?> getDetailsByOrigin(String originId, String userId, OriginType type){
    
  //   log.info("[getDetailsByOrigin] OriginType: {}, OriginId: {}", type, originId);
  //   if(type.equals(OriginType.FEED)){

  //     return getFeed(originId);
  //   }else if(type.equals(OriginType.PRODUCT)){
      
  //     return getProduct(originId);
  //   }
  //   return null;
  // } 

  // private ResponseEntity<?> getProduct(String originId){
    
  //   EntityProduct product = validateProduct(originId);
  //   ProductDto productDto  = inquireMapper.toProductDto(product);
  //   return ResponseEntity.status(HttpStatus.OK)
  //     .body(Response.builder().message("data found").data(productDto)
  //     .timestamp(DateTimeUtils.currentDateTimeUTCInString())
  //     .build());

  // }

  // private ResponseEntity<?> getFeed(String originId){

  //   Feed feed = validateFeed(originId);
  //   FeedDto feeds = inquireMapper.toFeedDto(feed);
  //   return ResponseEntity.status(HttpStatus.OK)
  //     .body(Response.builder().message("data found").data(feeds)
  //     .timestamp(DateTimeUtils.currentDateTimeUTCInString())
  //     .build());

  // }

  // private void validateOriginId(OriginType origin, String originId) {
  //   if (origin.equals(OriginType.FEED)) {
  //     validateFeed(originId);
  //   } else if (origin.equals(OriginType.PRODUCT)) {
  //     validateProduct(originId);
  //   }
  // }

  // private EntityProduct validateProduct(String originId){
  //   EntityProduct product = entityProductRepostitory.findById(originId)
  //         .orElseThrow(() -> new ResourceNotFoundException("product", "id", originId));

  //     if(!product.getIsActive()){
  //       log.info("[validateOriginId] product is deleted: {}", originId);
  //       throw new BadRequestException("Product is deleted");
  //     }
  //   return product;
  // }

  // private Feed validateFeed(String originId){
  //   Feed feed = feedRepository.findById(originId)
  //         .orElseThrow(() -> new ResourceNotFoundException("feed", "id", originId));
  //     if (!feed.getIsActive()) {
  //       log.info("[validateOriginId] postId: {} is deleted", originId);
  //       throw new BadRequestException("Post was deleted by user");
  //     }
  //     if (feed.getIsHide()) {
  //       log.info("[validateOriginId] postId: {} is reported", originId);
  //       throw new BadRequestException("Post is under review");
  //     }
  //     return feed;
  // }

  // private User validateUser(String userId) {
  //   return userRepository.findById(userId)
  //       .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
  // }
  
}


/*
 * InquireResponseDto
 * case 1: originId = null 
 * then it is normal message
 * 
 * case 2: orignId = not null
 * and originContent etc is there 
 * then it is feed inquire and show those content
 * 
 * case 3: originId = not null 
 * and originContent etc is null
 * then feed is either deleted or reported.
 */
