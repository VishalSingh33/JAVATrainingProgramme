package com.notification.service.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.notification.service.entity.InquireMessage;


@Repository
public interface InquireMessageRepository extends JpaRepository<InquireMessage, String>{
  
  // @Query(nativeQuery = true,
  //     value = "SELECT im.id as id, im.inquire_id as inquireId, " + 
  //     "im.inquire  ->> 'sender_id' as senderId, " + 
  //     "im.inquire ->> 'receiver_id' as receiverId, " + 
  //     "im.inquire ->> 'origin_id' as originId, " + 
  //     "im.inquire ->> 'origin_type' as originType, " + 
  //     "im.inquire ->> 'content' as content, " + 
  //     "im.inquire ->> 'status' as status, " + 
  //     "im.inquire ->> 'created_on' as createdOn, " + 
  //     "f.media_url[0] as originImg, f.content as originContent " + 
  //     "FROM inquire_message im " + 
  //     "left join feed f on " + 
  //     "im.inquire ->> 'origin_id' = f.id and f.is_active = true and f.is_hide = true " + 
  //     "WHERE im.inquire_id  = :inquireId " + 
  //     "ORDER BY im.inquire  ->> 'created_on' DESC ")
  @Query(nativeQuery = true, 
    value = "select * from inquire_message im where im.inquire_id = :inquireId " + 
    "and im.is_active = true order by im.created_on desc ")
  List<InquireMessage> findByInquireId(String inquireId, Pageable requestPage);

  // @Query(nativeQuery = true,  
  //   value ="SELECT COUNT(im.id) FROM inquire_message im " + 
  //   "WHERE im.inquire ->> 'sender_id' = :senderId " + 
  //   "AND im.inquire ->> 'receiver_id' = :receiverId " + 
  //   "AND im.inquire ->> 'status' = 'RECEIVED' ")
  @Query(nativeQuery = true,  
    value = "select count(id) from inquire_message im where im.sender_id = :senderId " + 
    "and im.receiver_id = :receiverId and " + 
    "im.status ilike 'RECEIVED' and im.is_active = true ")
  Long countReceivedMessageForSenderAndReceiver(String senderId, String receiverId);

  // @Query(nativeQuery = true,
  //   value= "SELECT COUNT(im.id) FROM inquire_message im " + 
  //   "WHERE im.inquire ->> 'sender_id' = :userId " + 
  //   "AND im.inquire ->> 'status' = 'RECEIVED' ")
  @Query(nativeQuery = true,
    value= "SELECT COUNT(im.id) FROM inquire_message im " + 
    "where im.receiver_id = :userId " + 
    "and im.is_active = true " + 
    "and im.status ilike 'RECEIVED' ")
  Long getUnReadCount(String userId);

  @Query(nativeQuery = true, 
    value = "select * from inquire_message im " + 
    "where inquire_id = :inquireRoomId " + 
    "and is_active = true " + 
    "order by created_on desc limit 1 ")
	InquireMessage findLastMessage(String inquireRoomId);

  @Modifying
  @Query(nativeQuery = true,
    value = "update inquire_message im set status = 'DELIVERED' " + 
      "where im.sender_id = :senderId and im.receiver_id = :receiverId " + 
      "and im.status = 'RECEIVED' and im.is_active = true ")
  void updateStatus(String senderId, String receiverId); //not working on server!! similar is used in page and company service

  @Query(nativeQuery = true, 
    value = "select * from inquire_message im where " + 
    "im.sender_id = :senderId and im.receiver_id = :receiverId and " + 
    "im.status = 'RECEIVED' and im.is_active = true ")
  List<InquireMessage> findAllUnReadMessages(String senderId, String receiverId);

  // @Query(nativeQuery = true,
  //   value = "SELECT im.id as id, im.inquire_id as inquireId, " + 
  //   "im.inquire  ->> 'sender_id' as senderId, " + 
  //   "im.inquire ->> 'receiver_id' as receiverId, " + 
  //   "im.inquire ->> 'origin_id' as originId, " + 
  //   "im.inquire ->> 'origin_type' as originType, " + 
  //   "im.inquire ->> 'content' as content, " + 
  //   "im.inquire ->> 'status' as status, " + 
  //   "im.inquire ->> 'created_on' as createdOn, " + 
  //   "f.media_url[0] as originImg, f.content as originContent " + 
  //   "FROM inquire_message im " + 
  //   "left join feed f on " + 
  //   "im.inquire ->> 'origin_id' = f.id and f.is_active = true and f.is_hide = true " + 
  //   "WHERE im.id = :id ")
  // IInquireResponseDto findResponseDtoById(String id); 

}
