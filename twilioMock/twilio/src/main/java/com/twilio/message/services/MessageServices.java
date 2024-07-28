package com.twilio.message.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.twilio.message.entities.MessageBox;
import com.twilio.message.repository.MessageRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import ClickSend.ApiClient;
// import ClickSend.ApiException;
// import java.nio.ByteBuffer;
// import ClickSend.Api.SmsApi;
// import ClickSend.Model.SmsMessage;
// import ClickSend.Model.SmsMessageCollection;
import org.springframework.core.env.Environment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageServices {

    private final ApiClient clickSendConfig;
    private final Environment env;
    private final MessageRepository messageRepository;

    @Value("${twilio.whatsappFrom}")
    private String whatsappFrom;

    @Value("${twilio.whatsappTo}")
    private String whatsappTo;

    // public static final String ACCOUNT_SID = System.getenv("accountSid");
    // public static final String AUTH_TOKEN = System.getenv("authToken");

    public void getSendMessages(int count) {
        // for (int i = 0; i < count; i++) {
        sendMessage("This is message number ");
        // }
    }

    private void sendMessage(String messageContent) {
        Message message = Message.creator(
                new PhoneNumber(whatsappTo),
                new PhoneNumber(whatsappFrom),
                messageContent).create();
        System.out.println("Sent message with SID: " + message.getSid());
    }

    public MessageBox createMessageEntity(String content) {
        MessageBox messageEntity = new MessageBox();
        messageEntity.setMessageId(UUID.randomUUID().toString());
        messageEntity.setMessage(content);
        messageEntity.setCreatedAt(LocalDateTime.now());
        messageEntity.setUpdatedAt(LocalDateTime.now());
        return messageEntity;
    }

    public ResponseEntity<String> sendWhatsApp(String messageDetails) {

        Message messages = Message.creator(
                new PhoneNumber(whatsappTo),
                new PhoneNumber(whatsappFrom),
                messageDetails)
                .create();
        // System.out.println("Message SID: " + messages.getSid());
        String SID = messages.getSid();

        MessageBox messageEntity = new MessageBox();
        messageEntity.setMessageId(UUID.randomUUID().toString());
        messageEntity.setMessage(messageDetails);
        messageEntity.setCreatedAt(LocalDateTime.now());
        messageEntity.setUpdatedAt(LocalDateTime.now());
        messageRepository.save(messageEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(SID);
        // return new ResponseEntity<>("Exception when calling SmsApi#smsSendPost",
        // HttpStatus.BAD_REQUEST);
    }

}
