package com.chat.room.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.chat.room.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    @Query(nativeQuery = true, value = " SELECT * FROM user WHERE user_id = :userId  " +
            " or user_id = :receiverId ")
    Optional<User> findBySenderIdAndReceiverId(@Param("userId") String senderId, @Param("receiverId") String receiverId);

    
}