package com.notification.service.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ManageNotificationRepository implements NotificationRepository {

  private Map<String, SseEmitter> userEmitterMap = new ConcurrentHashMap<>();

  @Override
  public void addOrReplaceEmitter(String userId, SseEmitter sseEmitter) {
    userEmitterMap.put(userId, sseEmitter);    
  }

  @Override
  public void remove(String userId) {
    if(userEmitterMap != null && userEmitterMap.containsKey(userId)) {
      log.info("Removing emitter for user: {}", userId);
      userEmitterMap.remove(userId);
    } else {
      log.debug("No emitter to remove for user: {}", userId);
    }
  }

  @Override
  public Optional<SseEmitter> get(String userId) {
    return Optional.ofNullable(userEmitterMap.get(userId));
  }
  
}
