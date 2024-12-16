package com.nvr.heartbeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nvr.heartbeat.dto.LoginUserDto;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.service.NvrService;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@RequestMapping("/nvr")
@RestController
public class NvrController {

	@Autowired
	private NvrService nvrService;

	@PostMapping("/status")
	@Timed(value = "nvr.id.status", histogram = true, percentiles = { 0.5, 0.75, 0.95, 0.99 }) 
	// Add tags for additional context
	@Counted(value = "nvr.id.status.count", description = "Counts the number of requests for NVR live status") 
	// Add tags for request count
	// @ExceptionCounted(value = "nvr.id.status.count.exceptions",
	// description = "Counts exceptions in the NVR live status method")
	public Response<?> nvrLiveStatus(@RequestBody List<String> nvrIds, @RequestHeader("email") String email,
			@RequestHeader("password") String password) {

		LoginUserDto loginUserDto = new LoginUserDto();
		loginUserDto.setEmail(email);
		loginUserDto.setPassword(password);

		return nvrService.nvrLiveStatus(nvrIds, loginUserDto);
	}

	@PostMapping("/{nvrId}")
	@Timed(value = "nvr.id.registration", histogram = true, percentiles = { 0.5, 0.75, 0.95, 0.99 }) 
	@Counted(value = "nvr.id.registration.count", description = "Counts the number of requests for NVR live status")
	// @ExceptionCounted(value = "nvr.id.registration.count.exceptions",
	// description = "Counts exceptions in the NVR registration")
	public Response<?> nvrCameraStatus(@Validated @PathVariable String nvrId,
			@RequestBody(required = false) @Validated String request, BindingResult bindingResult)
			throws JsonProcessingException {

		return nvrService.nvrCameraStatus(nvrId, request, bindingResult);
	}

}