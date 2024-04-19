package com.notification.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.http.ResponseEntity;
import com.notification.service.entity.Notification;
import com.notification.service.repository.NotificationRepository;
import com.notification.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final long eventsTimeout = 1000000L;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public SseEmitter createNotifications(String userId) {

        // log.info("[createEmitter] create emitor to produce data");

        SseEmitter sseEmitter = new SseEmitter(eventsTimeout);
        sseEmitter.onCompletion(() -> notificationRepository.remove(userId));
        sseEmitter.onTimeout(() -> notificationRepository.remove(userId));
        sseEmitter.onError(e -> {
        //   log.info("Create SseEitter exception", e);
          notificationRepository.remove(userId);
        });
        notificationRepository.addOrReplaceEmitter(userId, sseEmitter);
        return sseEmitter;
    }
    
}
