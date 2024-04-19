package com.notification.service.repository;

import java.util.Optional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationRepository {

    void remove(String userId);
  
    void addOrReplaceEmitter(String userId, SseEmitter sseEmitter);
  
    Optional<SseEmitter> get(String userId);
    
  
}