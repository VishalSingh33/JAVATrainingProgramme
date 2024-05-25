// package com.chat.room.service;

// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// import com.chat.room.entities.Message;
// import com.chat.room.repository.ChatRoomRepository;
// import com.chat.room.repository.UserRepository;

// import java.util.Optional;

// @RequiredArgsConstructor
// @Service
// public class ChatRoomService {

//     private final ChatRoomRepository chatRoomRepository;
//     private final UserRepository userRepository;

//     public Optional<String> getChatRoomId(
//             String senderId,
//             String receiverId,
//             boolean createNewRoomIfNotExists) {
//         return chatRoomRepository
//                 .findBySenderIdAndReceiverId(senderId, receiverId)
//                 .map(ChatMessage::getChatId)
//                 .or(() -> {
//                     if (createNewRoomIfNotExists) {
//                         var chatId = createChatId(senderId, receiverId);
//                         return Optional.of(chatId);
//                     }
//                     return Optional.empty();
//                 });
//     }

//     private String createChatId(String senderId, String receiverId) {
//         var chatId = String.format("%s_%s", senderId, receiverId);

//         Message senderRecipient = Message
//                 .builder()
//                 .chatId(chatId)
//                 .senderId(senderId)
//                 .receiverId(receiverId)
//                 .build();

//         Message recipientSender = Message
//                 .builder()
//                 .chatId(chatId)
//                 .senderId(receiverId)
//                 .receiverId(senderId)
//                 .build();

//         chatRoomRepository.save(senderRecipient);
//         chatRoomRepository.save(recipientSender);

//         return chatId;
//     }
// }