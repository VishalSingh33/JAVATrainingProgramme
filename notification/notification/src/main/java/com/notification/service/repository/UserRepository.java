package com.notification.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.notification.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(nativeQuery = true, value = "SELECT exists(select id FROM users " +
        "WHERE ((from_user_id = :authUserId and to_user_id = :userId) " +
        "or (from_user_id = :userId and to_user_id = :authUserId)) " +
        "and  status = 'accept') ")
    Boolean checkWhetherConnected(String authUserId, String userId);

}
