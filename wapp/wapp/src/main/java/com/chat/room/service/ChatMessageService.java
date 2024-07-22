package com.chat.room.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.chat.room.dto.ChatMessageResquestDto;
import com.chat.room.dto.MessageDto;
import com.chat.room.dto.MessageStatus;
import com.chat.room.entities.ChatMessage;
import com.chat.room.dto.ChatNotification;
import com.chat.room.entities.ChatRoom;
import com.chat.room.entities.InquireRoom;
import com.chat.room.entities.Message;
import com.chat.room.entities.User;
import com.chat.room.repository.ChatMessageRepository;
import com.chat.room.repository.ChatRoomRepository;
import com.chat.room.repository.InquireRoomRepository;
import com.chat.room.repository.MessageRepository;
import com.chat.room.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

        private final ChatMessageRepository chatRepository;
        private final ChatRoomRepository chatRoomRepository;
        private final MessageRepository messageRepository;
        // private final ChatRoomService chatRoomService;
        private final SimpMessagingTemplate messagingTemplate;
        private final UserRepository userRepository;
        private final InquireRoomRepository inquireRoomRepository;

        @Transactional(rollbackFor = Exception.class)
        public ResponseEntity<?> newProcess(ChatMessageResquestDto chatMessage) {

                User user = userRepository.findById(chatMessage.getSenderId())
                                .orElseThrow(() -> new RuntimeException(
                                                "User not found with id: " + chatMessage.getSenderId()));

                userRepository.findById(chatMessage.getReceiverId())
                                .orElseThrow(() -> new RuntimeException(
                                                "User not found with id: " + chatMessage.getReceiverId()));

                ChatMessage message = new ChatMessage();
                message.setMessageId(UUID.randomUUID().toString());
                message.setCreatedAt(LocalDateTime.now());
                // intialize message here and set values
                // message.setMessage(chatMessage.getContent());
                Message mass = new Message();
                mass.setMessageId(UUID.randomUUID().toString());
                mass.setCreatedOn(LocalDateTime.now());
                mass.setMessage(chatMessage.getMessage());
                mass.setSenderId(chatMessage.getSenderId());
                mass.setReceiverId(chatMessage.getReceiverId());
                mass.setStatus(MessageStatus.RECEIVED);
                message.setMessage(mass);
                String chatId = findOrCreateChatRoom(chatMessage.getSenderId(), chatMessage.getReceiverId());
                message.setChatId(chatId);

                InquireRoom inquireRoom = findOrCreateInquireRoom(chatMessage.getSenderId(),
                                chatMessage.getReceiverId());

                messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(), "/queue/messages",
                                new ChatNotification(message.getMessageId(), chatMessage.getSenderId(),
                                                chatMessage.getReceiverId()));

                ChatRoom chatRoom = chatRoomRepository.findById(chatId)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + chatId));

                chatRoom.setUpdatedOn(LocalDateTime.now());

                chatRepository.save(message);
                inquireRoomRepository.save(inquireRoom);
                messageRepository.save(mass);

                return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }

        public InquireRoom findOrCreateInquireRoom(String senderId, String receiverId) {

                InquireRoom inquireRoom = findInquireRoom(senderId, receiverId);
                if (ObjectUtils.isEmpty(inquireRoom)) {
                        // createNew chatRoom
                        String inquireRoomId = UUID.randomUUID().toString();
                        OffsetDateTime createdOn = new Date().toInstant().atOffset(ZoneOffset.UTC);

                        inquireRoom = new InquireRoom(inquireRoomId, senderId, receiverId, createdOn, createdOn);
                        // log.info("[findOrCreateInquireRoom] created new chatRoom:
                        // {}",inquireRoom.getId());
                        inquireRoomRepository.save(inquireRoom);
                }
                return inquireRoom;
        }

        public InquireRoom findInquireRoom(String senderId, String receiverId) {

                if (senderId.equals(receiverId)) {
                        throw new RuntimeException("You cannot chat with yourself");
                }
                InquireRoom inquireRoom = inquireRoomRepository.findByIds(senderId, receiverId);
                return inquireRoom;
        }

        public String findOrCreateChatRoom(String senderId, String receiverId) {

                // log.info("[findOrCreateChatRoom] sender: {}, receiver: {}", senderId,
                // receiverId);
                if (senderId.equals(receiverId)) {
                        throw new RuntimeException("You cannot chat with yourself");
                }
                String chatId = chatRoomRepository.findByIds(senderId, receiverId);
                if (!StringUtils.hasText(chatId)) {
                        // createNew chatRoom
                        // if they are connected then only create new chatRoom
                        chatId = UUID.randomUUID().toString();

                        ChatRoom chatRoom = new ChatRoom(chatId, senderId, receiverId, LocalDateTime.now(),
                                        LocalDateTime.now());
                        // log.info("[findOrCreateChatRoom] created new chatRoom: {}",chatRoom.getId());
                        chatRoomRepository.save(chatRoom);
                }
                return chatId;
        }

        public List<Message> findChatMessages(String senderId, String receiverId) {

                // var chatId = ChatRoomRepository.getChatRoomId(senderId, receiverId);
                // return chatId.map(chatRepository::findByChatId).orElse(new ArrayList<>());
                return null;
        }

        // public List<Message> findChatMessages(String senderId, String receiverId) {
        // String chatId = chatRoomService.getChatRoomId(senderId, receiverId, false)
        // .orElseThrow(() -> new RuntimeException("User not found with id: " + senderId
        // + " or " + receiverId));

        // return repository.findByChatId(chatId);
        // }

}