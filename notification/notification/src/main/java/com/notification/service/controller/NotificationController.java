package com.notification.service.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/admin/v1")
public interface NotificationController {

	@GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createNotifications(@PathVariable String userId);

	// @Operation(description = "This Feed Emitter Service work to send feed to
	// user")
	// @GetMapping("/emits/notifications")
	// public SseEmitter subscribeToNotifications(
	// @Parameter(description = "Z-AUTH-USERID is the current login user ID",
	// required = true)
	// @RequestHeader("Z-AUTH-USERID") String userId) {
	// log.info("[subscribeToNotifications] emit notification");
	// return notificationService.createEmitter(userId);

}