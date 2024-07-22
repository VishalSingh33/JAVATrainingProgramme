// package com.airlines.british.config;

// import java.io.IOException;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ResourceLoader;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import io.lettuce.core.ClientOptions;
// import io.lettuce.core.SslOptions;
// import io.lettuce.core.protocol.ProtocolVersion;
// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class RedisSSLConfiguration {

//   @Value("${spring.redis.host}")
//   private String redisHost;

//   @Value("${spring.redis.port}")
//   private int redisPort;

//   @Value("${spring.redis.password}")
//   private String redisPassword;

//   // @Value("${COMMON_DS_REDIS_CA_CERT}")
//   // private String redisCaCertPath;

//   @Value("${REDIS_TLS_ENABLED}")
//   private boolean sslEnabled;

//   @Value("${REDIS_CA_CERT}")
//   private String certFileLocation;

//   private final ResourceLoader resourceLoader;

//   @Bean
//   RedisConnectionFactory redisConnectionFactory() throws IOException {
//     RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//     redisStandaloneConfiguration.setHostName(redisHost);
//     redisStandaloneConfiguration.setPort(redisPort);
//     redisStandaloneConfiguration.setPassword(redisPassword);

//     LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettuceClientConfiguration
//         .builder();

//     if (sslEnabled) {
//       SslOptions sslOptions = SslOptions.builder()
//           .trustManager(resourceLoader.getResource("file:" + certFileLocation).getFile())
//           .build();

//       ClientOptions clientOptions = ClientOptions
//           .builder()
//           .sslOptions(sslOptions)
//           .protocolVersion(ProtocolVersion.RESP3)
//           .build();

//       lettuceClientConfigurationBuilder
//           .clientOptions(clientOptions)
//           .useSsl().disablePeerVerification();
//     }

//     LettuceClientConfiguration lettuceClientConfiguration = lettuceClientConfigurationBuilder.build();

//     return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
//   }

// }