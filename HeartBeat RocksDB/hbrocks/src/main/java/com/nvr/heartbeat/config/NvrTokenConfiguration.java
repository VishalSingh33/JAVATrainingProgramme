package com.nvr.heartbeat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nvr.heartbeat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class NvrTokenConfiguration {

    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    UserDetailsService userDetailsService() {
        logger.info("NHB_CONF_NTC_01 - Initializing UserDetailsService");
        return username -> {
            logger.info("NHB_CONF_NTC_01 - Looking up user by email: {}", username);
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> {
                        logger.warn("NHB_CONF_NTC_01 - User not found for email: {}", username);
                        return new UsernameNotFoundException("User not found");
                    });
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        logger.info("NHB_CONF_NTC_02 - Initializing BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        logger.info("NHB_CONF_NTC_03 - Initializing AuthenticationManager");
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        logger.info("NHB_CONF_NTC_04 - Initializing DaoAuthenticationProvider");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        logger.info("NHB_CONF_NTC_04 - DaoAuthenticationProvider configured with UserDetailsService and PasswordEncoder");
        return authProvider;
    }
}