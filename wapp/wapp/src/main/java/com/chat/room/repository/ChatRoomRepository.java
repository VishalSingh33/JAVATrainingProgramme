package com.chat.room.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.room.dto.ChatRoomDto;
import com.chat.room.entities.ChatMessage;
import com.chat.room.entities.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    Optional<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);

    @Query(nativeQuery = true, value = "SELECT id FROM chat_room WHERE (sender_id = :senderId AND receiver_id = :receiverId) "
            +
            " OR (sender_id = :receiverId AND receiver_id = :senderId)")
    String findByIds(String senderId, String receiverId);

    @Query(nativeQuery = true, value = "select cr.id as chatRoomId, " +
            "case " +
            "when receiver_id = :userId then sender_id " +
            "when sender_id = :userId then receiver_id " +
            "end as id, " +
            "u.full_name as fullName, u.img as profileImg, " +
            "u.title as profileTitle, cr.updated_on as updatedOn, " +
            "case " +
            "when uc.id is null then false " +
            "when uc.id is not null then true " +
            "end as isConnected " +
            "from chat_room cr " +
            "inner join users u on " +
            "case " +
            "when receiver_id = :userId then sender_id = u.id " +
            "when sender_id = :userId then receiver_id = u.id end " +
            "left join user_connection uc on " +
            "((uc.from_user_id = :userId and uc.to_user_id = u.id) " +
            "or (uc.to_user_id = :userId and uc.from_user_id =u.id)) and uc.status = 'accept' " +
            "left join block_user bu on " +
            "((bu.from_user_id = u.id and bu.to_user_id = :userId) " +
            "or (bu.to_user_id = u.id and bu.from_user_id = :userId)) " +
            "and bu.status = :status and bu.origin = :origin " +
            "where bu.id is null " +
            "and (receiver_id = :userId or sender_id = :userId ) " +
            "and cr.created_on <> cr.updated_on " +
            "order by cr.updated_on desc ")
    List<ChatRoomDto> getActiveChatRoom(String userId, String status, String origin, Pageable requestedPage);

}