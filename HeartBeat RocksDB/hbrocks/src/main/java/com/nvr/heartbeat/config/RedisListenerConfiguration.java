// package com.nvr.heartbeat.config;

// import org.springframework.data.redis.listener.RedisMessageListenerContainer;
// import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.listener.PatternTopic;
// import org.springframework.data.redis.connection.MessageListener;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisKeyValueAdapter;

// @Configuration
// @EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
// public class RedisListenerConfiguration {

//     @Bean
//     RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//         RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//         container.setConnectionFactory(connectionFactory);
//         container.addMessageListener(listenerAdapter, new PatternTopic("__keyevent@*__:expired"));
//         return container;
//     }

//     @Bean
//     MessageListenerAdapter listenerAdapter(MessageListener listener) {
//         return new MessageListenerAdapter(listener);
//     }

// }