package com.chat.room.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.room.entities.InquireMessage;


@Repository
public interface InquireMessageRepository extends JpaRepository<InquireMessage, String>{

}