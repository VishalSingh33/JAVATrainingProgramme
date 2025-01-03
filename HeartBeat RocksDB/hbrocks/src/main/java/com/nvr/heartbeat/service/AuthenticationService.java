package com.nvr.heartbeat.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.nvr.heartbeat.dto.LoginResponseDto;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.entites.User;
import com.nvr.heartbeat.exception.ResourceNotFoundException;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.helper.ResponseHelper;
import com.nvr.heartbeat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

        @Value("${user.email}")
        private String email;

        private final JwtService jwtService;
        private final UserRepository userRepository;
        private final AuthenticationManager authenticationManager;

        @Qualifier("redisTokenTemplate")
        private final RedisTemplate<String, String> redisTokenTemplate;
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public Response<LoginResponseDto> authenticate() {

                logger.info("NHB_SER_01 - Attempting to authenticate user with email: {}");

                User authenticatedUser = userRepository.authGetByMail(email)
                                .orElseThrow(() -> new RuntimeException("User not found with mail"));
                String jwtToken = jwtService.generateToken(authenticatedUser);
                LoginResponseDto loginResponse = new LoginResponseDto().setToken(jwtToken)
                                .setExpiresIn(jwtService.getExpirationTime());
                redisTokenTemplate.opsForValue().set(authenticatedUser.getEmail(), loginResponse.getToken(),
                                loginResponse.getExpiresIn(), TimeUnit.MILLISECONDS);
                return ResponseHelper.getSuccessResponse(loginResponse);
        }

        public User authenticate(LoginUserDto loginUserDto) {
                try {
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        loginUserDto.getEmail(),
                                                        loginUserDto.getPassword()));

                        // If authentication is successful, you can retrieve user details
                        return (User) authentication.getPrincipal();

                } catch (AuthenticationException e) {
                        // Handle authentication failure
                        throw new ResourceNotFoundException("Invalid email or password");
                }
        }

}
