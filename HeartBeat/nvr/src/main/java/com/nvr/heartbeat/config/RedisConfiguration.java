package com.nvr.heartbeat.config;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.timeout}")
	private int timeout;

	// @Value("${spring.redis.password}")
	// private String password;

	@Value("${spring.redis.cluster.nodes}")
	private List<String> clusterNodes;

	@Value("${spring.redis.lettuce.pool.max-active}")
	private int maxActive;

	@Bean
	public LettuceConnectionFactory redisClusterConnectionFactory() {
		Logger logger = LoggerFactory.getLogger(this.getClass());

		logger.info("NHB_NS_OOR1 - Initializing Redis Cluster Configuration with nodes: {}", clusterNodes);

		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
		logger.info("NHB_NS_OOR1 - Setting max redirects to: {}", maxActive);
		redisClusterConfiguration.setMaxRedirects(maxActive);
		// redisClusterConfiguration.setPassword(password);
		
		// Support adaptive cluster topology refresh and static refresh source
		// In case a master node failure this configuration manage the whole topology
		// for otherher nodes.
		logger.info("NHB_NS_OOR1 - Configuring cluster topology refresh options.");
		ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
				.enablePeriodicRefresh().enableAllAdaptiveRefreshTriggers().refreshPeriod(Duration.ofSeconds(timeout))
				.build();
		logger.info("NHB_NS_OOR1 - Building Cluster Client Options with the topology refresh options.");
		ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
				.topologyRefreshOptions(clusterTopologyRefreshOptions).build();
		logger.info("NHB_NS_OOR1 - Setting Lettuce client configuration to read from any node.");
		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
				.readFrom(ReadFrom.ANY).clientOptions(clusterClientOptions).build();
		logger.info("NHB_NS_OOR1 - Returning LettuceConnectionFactory.");
		return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
	}

	@Primary
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, List<Map<String, Object>>> redisClusterTemplate(
			@Qualifier("redisClusterConnectionFactory") LettuceConnectionFactory redisConnectionFactory) {
		Logger logger = LoggerFactory.getLogger(this.getClass());

		logger.info("NHB_NS_OOR2 - Configuring Jackson2JsonRedisSerializer for List<Map<String, Object>>.");
		Jackson2JsonRedisSerializer<List<Map<String, Object>>> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						TypeFactory.defaultInstance().constructMapType(Map.class, String.class, Object.class)));
		logger.info("NHB_NS_OOR2 - Setting up ObjectMapper with visibility settings.");
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		logger.info("NHB_NS_OOR2 - Initializing RedisTemplate.");
		RedisTemplate<String, List<Map<String, Object>>> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		logger.info("NHB_NS_OOR2 - Setting key and value serializers for RedisTemplate.");
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		logger.info("NHB_NS_OOR2 - Finalizing RedisTemplate configuration with afterPropertiesSet.");
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean(name = "redisTokenTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
        return redisTemplate;
    }

}