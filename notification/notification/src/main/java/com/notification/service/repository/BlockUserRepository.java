// package com.notification.service.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
// import com.zyapaar.chatservice.entities.BlockUser;

// @Repository
// public interface BlockUserRepository extends JpaRepository<BlockUser, String>{

//   @Query(nativeQuery = true, 
//     value = "select exists (select bu.status " + 
//     "from block_user bu where " + 
//     "((bu.from_user_id = :authUserId and bu.to_user_id = :userId) " + 
//     "or " + 
//     "(bu.from_user_id = :userId and bu.to_user_id = :authUserId)) " + 
//     "and bu.status = :status " + 
//     "and bu.origin = :origin) ")
//   Boolean isBlockedUser(String authUserId, String userId, String status, String origin);
  
// }
