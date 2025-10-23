package com.nvr.heartbeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nvr.heartbeat.helper.Response;
import com.nvr.heartbeat.service.NvrService;

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
	public Response<?> nvrLiveStatus(@RequestBody List<String> nvrIds) {

		return nvrService.nvrLiveStatus(nvrIds);
	}

	@PostMapping("/{nvrId}")
	public Response<?> nvrCameraStatus(@PathVariable String nvrId,
			@RequestBody(required = false) @Validated String request, BindingResult bindingResult)
			throws JsonProcessingException {

		return nvrService.nvrCameraStatus(nvrId, request, bindingResult);
	}

}