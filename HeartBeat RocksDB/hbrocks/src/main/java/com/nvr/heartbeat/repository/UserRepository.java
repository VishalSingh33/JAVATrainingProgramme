package com.nvr.heartbeat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nvr.heartbeat.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "select * from users where email = :email")
    Optional<User> authGetByMail(@Param("email") String email);

}
