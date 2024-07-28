package com.twilio.message.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.message.entities.MessageBox;
import com.twilio.message.services.MessageServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ManageMessageController implements MessageController {

    private final MessageServices messagingService;

    @Override
    public String getSendMessages() {

        messagingService.getSendMessages(1000);
        return "Messages sent!";
    }

    @Override
    public ResponseEntity<String> sendWhatsApp(String messageDetails) {

        return messagingService.sendWhatsApp(messageDetails);
    }

}
