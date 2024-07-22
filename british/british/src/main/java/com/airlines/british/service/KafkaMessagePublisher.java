// package com.airlines.british.service;

// import com.airlines.british.entites.User;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.apache.kafka.clients.producer.RecordMetadata;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.support.SendResult;
// import org.springframework.stereotype.Service;

// import java.util.concurrent.CompletableFuture;

// @Service
// public class KafkaMessagePublisher {

//     @Autowired
//     private KafkaTemplate<String, Object> template;
//     private static final Logger log = LoggerFactory.getLogger(User.class);

//     public void sendMessageToTopic(String message) {
//         CompletableFuture<SendResult<String, Object>> future = template.send("chat", message);
//         future.whenComplete((result, ex) -> {
//             if (ex == null) {
//                 System.out.println("Sent message=[" + message +
//                         "] with offset=[" + result.getRecordMetadata().offset() + "]");

//                 RecordMetadata recordMetadata = result.getRecordMetadata();
//                 log.info("Received new metadata. \n" +
//                         "Topic: " + recordMetadata.topic() + "\n" +
//                         "Partition: " + recordMetadata.partition() + "\n" +
//                         "Offset: " + recordMetadata.offset() + "\n" +
//                         "Timestamp: " + recordMetadata.timestamp());
//             } else {
//                 log.error("Error while producing", ex.getMessage());
//                 System.out.println("Unable to send message=[" +
//                         message + "] due to : " + ex.getMessage());
//             }
//         });
//     }

//     public void sendEventsToTopic(User customer) {
//         try {
//             CompletableFuture<SendResult<String, Object>> future = template.send("chat", customer);
//             future.whenComplete((result, ex) -> {
//                 if (ex == null) {
//                     System.out.println("Sent message=[" + customer.toString() +
//                             "] with offset=[" + result.getRecordMetadata().offset() + "]");

//                     RecordMetadata recordMetadata = result.getRecordMetadata();
//                     log.info("Received new metadata. \n" +
//                             "Topic: " + recordMetadata.topic() + "\n" +
//                             "Partition: " + recordMetadata.partition() + "\n" +
//                             "Offset: " + recordMetadata.offset() + "\n" +
//                             "Timestamp: " + recordMetadata.timestamp());
//                 } else {
//                     log.error("Error while producing", customer.toString() + "due to " + ex.getMessage());
//                     System.out.println("Unable to send message=[" +
//                             customer.toString() + "] due to : " + ex.getMessage());
//                 }
//             });
//         } catch (Exception ex) {
//             // System.out.println("ERROR : " + ex.getMessage());
//             log.error("Exception occurred while publishing message", ex.getMessage());
//         }
//     }
// }