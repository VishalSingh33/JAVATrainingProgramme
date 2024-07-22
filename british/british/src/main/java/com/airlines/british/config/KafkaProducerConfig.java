// package com.airlines.british.config;

// import org.apache.kafka.clients.admin.NewTopic;
// import org.apache.kafka.clients.consumer.ConsumerConfig;
// import org.apache.kafka.clients.consumer.KafkaConsumer;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.apache.kafka.common.serialization.StringDeserializer;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.apache.poi.common.usermodel.GenericRecord;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.core.DefaultKafkaProducerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.core.ProducerFactory;
// import org.springframework.kafka.support.serializer.JsonSerializer;
// import java.util.HashMap;
// import java.util.Map;

// @Configuration
// public class KafkaProducerConfig {

//     @Bean
//     public NewTopic createTopic(){
//         return new NewTopic("chat", 3, (short) 1);
//     }

//     @Bean
//     public Map<String,Object> producerConfig(){
        
//         Map<String,Object> props=new HashMap<>();
//         props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                 "localhost:9092");
//         props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                 StringSerializer.class);
//         props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                 StringDeserializer.class);
//         return props;
        
//     }

//     @Bean
//     public ProducerFactory<String,Object> producerFactory(){
//         return new DefaultKafkaProducerFactory<>(producerConfig());
//     }

//     @Bean
//     public KafkaTemplate<String,Object> kafkaTemplate(){
//         return new KafkaTemplate<>(producerFactory());
//     }

// }



// https://codingharbour.com/apache-kafka/guide-to-apache-avro-and-kafka/

// https://www.youtube.com/watch?v=u0kRK-qbopk&list=PLVz2XdJiJQxwpWGoNokohsSW2CysI6lDc&index=17&ab_channel=JavaTechie