package com.twilio.message.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@CrossOrigin("*")
@RequestMapping("/api/user/v1")
public interface MessageController {

    @PostMapping("/processSMS")
    public ResponseEntity<String> sendWhatsApp(@RequestHeader String messageDetails);

    @GetMapping("/send-messages")
    public String getSendMessages();

}
