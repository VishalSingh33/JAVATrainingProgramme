// package com.airlines.british.config;

// import java.time.Duration;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.cache.RedisCacheManager;
// //import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.convert.RedisCustomConversions;
// import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

// @Configuration
// public class RedisConfig {

//   private String redisHost = "localhost";
//   private int redisPort = 6379;
//   private String redisPassword = "wsl";
//   private boolean redisSsl = true;
//   private boolean redisTlsEnabled = true;

//   // @Value("${redis.host}")
//   // private String redisHost;

//   // @Value("${redis.port}")
//   // private int redisPort;

//   // @Value("${redis.password}")
//   // private String redisPassword;

//   // @Value("${redis.ssl}")
//   // private boolean redisSsl;

//   // @Value("${redis.tls-enabled}")
//   // private boolean redisTlsEnabled;

//   @Bean
//   public LettuceConnectionFactory redisConnectionFactory() {
//     RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

//     return new LettuceConnectionFactory(configuration);
//   }

//   @Bean
//   public RedisCacheManager cacheManager() {
//     RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

//     return RedisCacheManager.builder(redisConnectionFactory())
//         .cacheDefaults(cacheConfig)
//         .withCacheConfiguration("user", myDefaultCacheConfig(Duration.ofMinutes(5)))
//         .withCacheConfiguration("user", myDefaultCacheConfig(Duration.ofMinutes(1)))
//         .build();
//   }

//   private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
//     return RedisCacheConfiguration
//         .defaultCacheConfig()
//         .entryTtl(duration)
//         .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//   }


// }
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