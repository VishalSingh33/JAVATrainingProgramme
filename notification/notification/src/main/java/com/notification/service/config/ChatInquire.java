// package com.notification.service.config;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.converter.DefaultContentTypeResolver;
// import org.springframework.messaging.converter.MappingJackson2MessageConverter;
// import org.springframework.messaging.converter.MessageConverter;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.util.MimeTypeUtils;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// import java.util.List;

// @Configuration
// @EnableWebSocketMessageBroker
// public class ChatInquire implements WebSocketMessageBrokerConfigurer {

//     @SuppressWarnings("null")
//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry config) {
//         config.enableSimpleBroker("/chat-inquire/user");
//         config.setApplicationDestinationPrefixes("/chat-inquire");
//         config.setUserDestinationPrefix("/chat-inquire/user");
//     }

//     @SuppressWarnings("null")
//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         registry.addEndpoint("/chat-inquire/ws")
//                 .setAllowedOriginPatterns("*")
//                 .withSockJS();
//     }

//     @SuppressWarnings("null")
//     @Override
//     public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//         DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
//         resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//         MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//         converter.setObjectMapper(new ObjectMapper());
//         converter.setContentTypeResolver(resolver);
//         messageConverters.add(converter);
//         return false;
//     }

// }
