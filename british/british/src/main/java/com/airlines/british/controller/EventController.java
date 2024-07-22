// package com.airlines.british.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.web.bind.annotation.*;
// import com.airlines.british.entites.User;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import java.util.concurrent.CompletableFuture;
// import org.apache.kafka.clients.producer.RecordMetadata;
// import org.springframework.kafka.support.SendResult;
// import org.springframework.util.concurrent.ListenableFuture;
// import org.springframework.util.concurrent.ListenableFutureCallback;
// import com.airlines.british.service.KafkaMessagePublisher;

// @SuppressWarnings("deprecation")
// @RestController
// @RequestMapping("/producer-app")
// public class EventController {

//     @Autowired
//     private KafkaMessagePublisher publisher;
    
//     @Autowired
//     private KafkaTemplate<String, Object> kafkaTemplate;

//     @SuppressWarnings("unused")
//     private static final Logger log = LoggerFactory.getLogger(EventController.class);

//     @GetMapping("/publisher/{message}")
//     public ResponseEntity<?> publishMessage(@PathVariable String message) {
//     try {
//     for (int i = 0; i <= 10000; i++) {
//     publisher.sendMessageToTopic(message + " : " + i);
//     }
//     return ResponseEntity.ok("message published successfully ..");
//     } catch (Exception ex) {
//     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//     .build();
//     }
//     }

//     // Cluster ID: CQwc31owRoWu15mRG0ZjZg

//     // @GetMapping("/publish/{message}")
//     // public ResponseEntity<?> publishMessage(@PathVariable User message) {
//     //     try {
//     //         for (int i = 0; i <= 10000; i++) {
//     //             CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("chat" , message);

//     //             future.whenComplete((result, ex) -> {
//     //                 if (ex == null) {
//     //                     RecordMetadata recordMetadata = result.getRecordMetadata();
//     //                     log.info("Received new metadata. \n" +
//     //                             "Topic: " + recordMetadata.topic() + "\n" +
//     //                             "Partition: " + recordMetadata.partition() + "\n" +
//     //                             "Offset: " + recordMetadata.offset() + "\n" +
//     //                             "Timestamp: " + recordMetadata.timestamp());
//     //                 } else {
//     //                     log.error("Error while producing", ex);
//     //                 }
//     //             });
//     //         }
//     //         return ResponseEntity.ok("Message published successfully.");
//     //     } catch (Exception ex) {
//     //         log.error("Exception occurred while publishing message", ex);
//     //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//     //     }
//     // }

//     @PostMapping("/publish")
//     public void sendEvents(@RequestBody User customer) {
//         publisher.sendEventsToTopic(customer);
//     }

// }