package com.notification.service.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.notification.service.dto.InquireRoomDto;
import com.notification.service.entity.InquireRoom;

@Repository
public interface InquireRoomRepository extends JpaRepository<InquireRoom, String>{
  
  @Query(nativeQuery = true, 
    value = "SELECT * FROM inquire_room WHERE " + 
    "(sender_id = :senderId AND receiver_id = :receiverId) " + 
    "OR (sender_id = :receiverId AND receiver_id = :senderId) ")
  InquireRoom findByIds(String senderId, String receiverId);

  @Query(nativeQuery = true, 
      value = "select ir.id as chatRoomId, " + 
      "case when receiver_id = :userId then sender_id " + 
      "when sender_id = :userId then receiver_id end as id, " + 
      ":userId as myId, " +
      "u.full_name as fullName, u.img as profileImg, " + 
      "u.title as profileTitle,ir.updated_on as updatedOn " + 
      "from inquire_room ir " + 
      "inner join users u on case " + 
      "when ir.receiver_id = :userId then ir.sender_id = u.id " + 
      "when ir.sender_id = :userId then ir.receiver_id = u.id end " + 
      "left join block_user bu on " + 
      "((bu.from_user_id = u.id and bu.to_user_id = :userId) " + 
      "or (bu.to_user_id = u.id and bu.from_user_id = :userId)) " + 
      "and bu.status = :status and bu.origin = :origin " + 
      "where bu.id is null " + 
      "and " + 
      "(receiver_id = :userId or sender_id = :userId ) " + 
      "and ir.created_on <> ir.updated_on " + 
      "order by ir.updated_on desc ")
  List<InquireRoomDto> inquireChatRoom(String userId, String status, 
      String origin, Pageable requestedPage);
}
