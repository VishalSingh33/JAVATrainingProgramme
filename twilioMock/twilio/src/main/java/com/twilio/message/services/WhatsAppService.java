package com.twilio.message.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.whatsappFrom}")
    private String whatsappFrom;

    @Value("${twilio.whatsappTo}")
    private String whatsappTo;

    public ResponseEntity<String> sendMessage(String message) {

        // for (int i = 0; i < 100; i++) 
        Message messages = Message.creator(
                new PhoneNumber(whatsappTo),
                new PhoneNumber(whatsappFrom),
                message)
                .create();
        System.out.println("Message SID: " + messages.getSid());
        return ResponseEntity.ok("Messages sent successfully");

    }
    
}
