package com.twilio.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.message.services.WhatsAppService;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    // @GetMapping("/send")
    // public ResponseEntity<String> sendMessages(String message) {
    //     whatsAppService.sendMessage(message);
    //     return ResponseEntity.ok("Messages sent successfully");
    // }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessages(@RequestHeader String message) {
       
        return whatsAppService.sendMessage(message);
    }


}
