package com.nvr.heartbeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nvr.heartbeat.service.HeartBeatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.dto.RegistrationIdDto;
import com.nvr.heartbeat.helper.Response;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@RequestMapping("/device")
@RestController
public class HeartBeatController {

    @Autowired
    private HeartBeatService heartBeatService;

    @PostMapping("/status")
    @Timed(value = "device.id.status", histogram = true, percentiles = { 0.5, 0.75, 0.95, 0.99 })
    @Counted(value = "device.id.status.count", description = "Counts the number of requests for NVR live status")
    // Add tags for request count
    // @ExceptionCounted(value = "device.id.status.count.exceptions",
    // description = "Counts exceptions in the device live status method")
    public Response<?> nvrLiveStatus(@RequestBody List<String> deviceIds, @RequestHeader("email") String email,
            @RequestHeader("password") String password) {

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail(email);
        loginUserDto.setPassword(password);

        return heartBeatService.heartBeatLiveStatus(deviceIds, loginUserDto);
    }

    @Timed(value = "device.id.registration", histogram = true, percentiles = { 0.5, 0.75, 0.95, 0.99 })
    @Counted(value = "device.id.registration.count", description = "Counts the number of requests for live status")
    @PostMapping("/register")
    public Response<?> registerDevice(@RequestBody RegistrationIdDto registrationIdDto, BindingResult bindingResult)
            throws JsonProcessingException {

        return heartBeatService.registerDevice(registrationIdDto, bindingResult);
    }

}