package com.notification.service.mapper;

import java.time.OffsetDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.notification.service.dto.ChatMessageResponseDto;
import com.notification.service.dto.ChatMessageResquestDto;
import com.notification.service.dto.MessageStatus;
import com.notification.service.entity.ChatMessage;
import com.notification.service.entity.Message;

@Mapper
public interface MessageMapper {

  @Mapping(target = "sender_id", source = "dto.senderId")
  @Mapping(target = "receiver_id", source = "dto.receiverId")
  Message update(ChatMessageResquestDto dto, OffsetDateTime created_on,
      MessageStatus status);

  @Mapping(target = "senderId", source = "chat.message.sender_id")
  @Mapping(target = "receiverId", source = "chat.message.receiver_id")
  @Mapping(target = "content", source = "chat.message.content")
  @Mapping(target = "status", source = "chat.message.status")
  @Mapping(target = "createdOn", source = "chat.message.created_on")
  ChatMessageResponseDto toMessageDto(ChatMessage chat); //used in findById
      
  List<ChatMessageResponseDto> toMessageDtoList(List<ChatMessage> messages); //used in findChatMessge
  
}
