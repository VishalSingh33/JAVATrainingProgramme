package com.nvr.heartbeat.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvr.heartbeat.dto.LoginResponseDto;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.entites.User;
import com.nvr.heartbeat.service.AuthenticationService;
import com.nvr.heartbeat.service.JwtService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    
    @Qualifier("redisTemplate")
    private final RedisTemplate<String, String> redisTokenTemplate;
    
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
       
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDto loginResponse = new LoginResponseDto().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        redisTokenTemplate.opsForValue().set(loginUserDto.getEmail(), loginResponse.getToken(), loginResponse.getExpiresIn(), TimeUnit.MILLISECONDS);
        return ResponseEntity.ok(loginResponse);
    }
}
//     "email":"vis@gmail.com",
//     "password":"vis@123",
//     "fullName":"vishal"
