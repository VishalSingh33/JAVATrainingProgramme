package com.nvr.heartbeat.config;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

	@Bean
	public RedisTemplate<String, List<Map<String, Object>>> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, List<Map<String, Object>>> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

		// Set key and value serializers
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));
		// template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return template;
	}
}
