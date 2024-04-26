package com.notification.service.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import com.notification.service.dto.FeedDto;
import com.notification.service.dto.InquireMessageDto;
import com.notification.service.dto.InquireResponseDto;
import com.notification.service.dto.InquireRoomDto;
import com.notification.service.dto.InquireRoomResponse;
import com.notification.service.dto.OriginType;
import com.notification.service.dto.ProductDto;
import com.notification.service.entity.Content;
// import com.notification.service.entity.EntityProduct;
// import com.notification.service.entity.Feed;
import com.notification.service.entity.InquireMessage;
import com.notification.service.repository.InquireMessageRepository;
// import com.notification.commons.utils.TimeCount;

@Mapper(componentModel = "spring")
public abstract class InquireMapper {

  @Autowired
  InquireMessageRepository inquireMessageRepository;

  // @Mapping(target = "sender_id", source = "dto.senderId")
  // @Mapping(target = "receiver_id", source = "dto.receiverId")
  // // @Mapping(target = "origin_id", source = "dto.originId")
  // // @Mapping(target = "origin_type", source = "dto.originType")
  // @Mapping(target = "content", source = "dto.content")
  // MessageDto create(InquireMessageDto dto, OffsetDateTime created_on,
  // MessageStatus status);

  String map(OriginType originType) {
    if (ObjectUtils.isEmpty(originType)) {
      return null;
    }
    return originType.type();
  }

  @Mapping(target = "origin", source = "inquire.originType")
  @Mapping(target = "origin_id", source = "inquire.originId")
  @Mapping(target = "message", source = "message")
  public abstract Content createContent(InquireMessageDto inquire);

  @Mapping(target = "message", source = "message.content.message")
  @Mapping(target = "originId", source = "message.content.origin_id")
  @Mapping(target = "origin", source = "message.content.origin")
  @Mapping(target = "createdOn", source = "createdOn")
  @Mapping(target = "updatedOn", source = "createdOn")
  public abstract InquireResponseDto toInquireMessage(InquireMessage message);

  public abstract List<InquireResponseDto> toInquireMessage(List<InquireMessage> message);

  // public abstract FeedDto toFeedDto(Feed feed);

  @Mapping(target = "entityName", source = "product.entity.name")
  @Mapping(target = "entityId", source = "product.entity.id")
  // public abstract ProductDto toProductDto(EntityProduct product);

  @Mapping(target = "count", expression = "java(toCount(inquireRoom))")
  @Mapping(target = "message", expression = "java(toMessage(inquireRoom))")
  @Mapping(target = "updateOn", source = "updatedOn")
  public abstract InquireRoomResponse toInquireRoomResponse(InquireRoomDto inquireRoom);

  public abstract List<InquireRoomResponse> toInquireRoomResponse(List<InquireRoomDto> inquireRoom);

  InquireResponseDto toMessage(InquireRoomDto inquireRoom) {

    InquireMessage message = inquireMessageRepository.findLastMessage(inquireRoom.getChatRoomId());
    if (ObjectUtils.isEmpty(message)) {
      return null;
    } else {
      return this.toInquireMessage(message);
    }
  }

  Long toCount(InquireRoomDto inquireRoom) {
    return inquireMessageRepository.countReceivedMessageForSenderAndReceiver(inquireRoom.getId(),
        inquireRoom.getMyId());
  }

  String utilTime(OffsetDateTime updatedOn) {
    // return TimeCount.timeFromUpload(updatedOn.toInstant().toEpochMilli());
    Instant instant = updatedOn.toInstant(); // Convert OffsetDateTime to Instant
    long epochMilli = instant.toEpochMilli(); // Convert Instant to milliseconds since the epoch
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneOffset.UTC);
    String formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    System.out.println("Formatted Time: " + formattedTime);
    return formattedTime; // Return the result
  }

}
