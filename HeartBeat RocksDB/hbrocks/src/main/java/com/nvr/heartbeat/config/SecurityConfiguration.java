package com.nvr.heartbeat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        logger.info("SECURITY_CONFIG_INIT - Initializing SecurityFilterChain");
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/auth/*").permitAll() // Use new DSL-compatible method
                .antMatchers("/**").permitAll()
                .antMatchers("/*").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            logger.info("NHB_CONF_SC_01 - Configuring authorization rules");
            logger.info("NHB_CONF_SC_01 - Added JWT Authentication Filter before UsernamePasswordAuthenticationFilter");

        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*")); // Update to allow specific origins in production
        logger.info("NHB_CONF_SC_02 - Allowed origins set to: {}", configuration.getAllowedOrigins());

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        logger.info("NHB_CONF_SC_02 - Allowed methods set to: {}", configuration.getAllowedMethods());

        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "email", "password", "token"));
        logger.info("NHB_CONF_SC_02 - Allowed headers set to: {}", configuration.getAllowedHeaders());

        configuration.setExposedHeaders(List.of("Authorization"));
        logger.info("NHB_CONF_SC_02 - Exposed headers set to: {}", configuration.getExposedHeaders());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);
        logger.info("NHB_CONF_SC_02 - Registered CORS configuration for all paths");

        return source;
    }
}