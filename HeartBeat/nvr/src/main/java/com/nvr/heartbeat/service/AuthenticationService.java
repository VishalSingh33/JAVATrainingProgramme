package com.nvr.heartbeat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.dto.RegisterUserDto;
import com.nvr.heartbeat.entites.User;
import com.nvr.heartbeat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public User signup(RegisterUserDto input) {
    logger.info("NHB_SER_01 - Attempting to register user with email: {}", input.getEmail());
        User user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));
    logger.info("USER_SIGNUP_SUCCESS - User registered successfully with email: {}", user.getEmail());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {

        logger.info("NHB_SER_02 - Attempting to authenticate user with email: {}", input.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
