package com.nvr.heartbeat.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nvr.heartbeat.dto.LoginResponseDto;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    @Scheduled(cron = "0 0 7 * * ?")
    @PostMapping("/login")
    public Response<LoginResponseDto> authenticate() {

        return authenticationService.authenticate();
    }
}