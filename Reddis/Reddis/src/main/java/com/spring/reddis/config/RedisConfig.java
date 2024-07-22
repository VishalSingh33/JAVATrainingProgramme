package com.spring.reddis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }
// C:\>Wsl
// wsl@DESKTOP-NI8PH40:/mnt/c$ sudo service redis-server start
// [sudo] password for wsl:
// wsl@DESKTOP-NI8PH40:/mnt/c$ redis-cli
// 127.0.0.1:6379> ping
// PONG
// 127.0.0.1:6379>

// // Enter new UNIX username: wsl
// // New password: wsl
// // set name vishal

// // open c://wsl at terminal in windows
// // wsl --install
// // ls
// // open the linux terminal and check wsl -l -v
// // back to winodws terminal (with green-hihglight)wsl@DESKTOP-NI8PH40:
// // curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg
// // echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
// // sudo apt-get update
// // sudo apt-get install redis

// // sudo service redis-server start // ->Connect to Redis
// // You can test that your Redis server is running by connecting with the Redis CLI:
// // redis-cli 
// // 127.0.0.1:6379> ping
// // PONG
}
