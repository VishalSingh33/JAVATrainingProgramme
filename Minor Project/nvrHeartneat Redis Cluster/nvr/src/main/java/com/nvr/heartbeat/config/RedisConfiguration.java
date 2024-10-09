package com.nvr.heartbeat.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.cluster.nodes}")
	private List<String> clusterNodes;

	@Value("${spring.redis.lettuce.pool.max-active}")
	private int maxActive;

//	@Bean
//	public RedisTemplate<String, List<Map<String, Object>>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//	
//		RedisTemplate<String, List<Map<String, Object>>> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		
//		// Set key and value serializers
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));
//		// template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//		return template;
//	}

	@Bean
	public LettuceConnectionFactory redisClusterConnectionFactory() {

		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);

		redisClusterConfiguration.setMaxRedirects(maxActive);
		redisClusterConfiguration.setPassword(password);
		// Support adaptive cluster topology refresh and static refresh source
		// In case a master node failure this configuration manage the whole topology
		// for otherher nodes.
		ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
				.enablePeriodicRefresh().enableAllAdaptiveRefreshTriggers().refreshPeriod(Duration.ofSeconds(timeout))
				.build();

		ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
				.topologyRefreshOptions(clusterTopologyRefreshOptions).build();

		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
				.readFrom(ReadFrom.ANY).clientOptions(clusterClientOptions).build();

		return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
	}

	@Bean
	public RedisTemplate<String, List<Map<String, Object>>> redisClusterTemplate(
			@Qualifier("redisClusterConnectionFactory") LettuceConnectionFactory redisConnectionFactory) {

		Jackson2JsonRedisSerializer<List<Map<String, Object>>> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						TypeFactory.defaultInstance().constructMapType(Map.class, String.class, Object.class)));

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		RedisTemplate<String, List<Map<String, Object>>> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
//vishal33@MSI:/mnt/c/Users/raguv$ sudo nano /etc/redis/redis.conf    // Rename it redis-6382, it will automatically create a copy
//vishal33@MSI:/mnt/c/Users/raguv$ sudo mkdir -p /var/lib/redis/6382
//vishal33@MSI:/mnt/c/Users/raguv$ sudo chown redis:redis /var/lib/redis/6382
//vishal33@MSI:/mnt/c/Users/raguv$ sudo mkdir -p /var/log/redis
//vishal33@MSI:/mnt/c/Users/raguv$ sudo chown redis:redis /var/log/redis
//vishal33@MSI:/mnt/c/Users/raguv$ sudo redis-server /etc/redis/redis-6382.conf
//vishal33@MSI:/mnt/c/Users/raguv$ ps aux    // to check all ports is listening