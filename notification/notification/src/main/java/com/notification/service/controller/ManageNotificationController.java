package com.notification.service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.extern.slf4j.Slf4j;
import com.notification.service.service.NotificationService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.Parameter;
// import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

// @Tag(name = "Notification emit APIs")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ManageNotificationController implements NotificationController {

  private final NotificationService notificationService;

  @Override
  public SseEmitter createNotifications(String userId) {
   
    return notificationService.createNotifications(userId);  }
}
