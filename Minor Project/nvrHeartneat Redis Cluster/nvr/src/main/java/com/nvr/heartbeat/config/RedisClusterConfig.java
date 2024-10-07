//package com.nvr.heartbeat.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisClusterConfig {
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration()
//                .clusterNode("127.0.0.1", 7000)
//                .clusterNode("127.0.0.1", 7001)
//                .clusterNode("127.0.0.1", 7002);
//        
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(10);
//        poolConfig.setMaxIdle(10);
//        poolConfig.setMinIdle(2);
//
//        return new JedisConnectionFactory(clusterConfig, poolConfig);
//    }
//}
