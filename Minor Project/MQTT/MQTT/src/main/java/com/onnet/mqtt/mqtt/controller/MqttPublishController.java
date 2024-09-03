package com.onnet.mqtt.mqtt.controller;

import java.util.concurrent.ExecutionException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.onnet.mqtt.helper.Response;
import com.onnet.mqtt.mqtt.service.MqttPublishService;

@RestController
@CrossOrigin("*")
@RequestMapping("/mqtt")
public class MqttPublishController {

	@Autowired
	private MqttPublishService mqttService;

	@PostMapping("/cctvInfo/publish")
	public Response<?> publishMessageStreaming(@RequestBody @Validated String req, BindingResult bindingResult)
			throws MqttException, JsonMappingException, JsonProcessingException, InterruptedException,
			ExecutionException {

		return mqttService.publishMessageStreaming(req, bindingResult);
	}

	@PostMapping("/cctvInfo/acknowledgeNo")
	public Response<?> publishedMessageAck(@RequestBody @Validated Response<?> req)
			throws InterruptedException, MqttException, JsonProcessingException {

		return mqttService.publishedMessageAck(req);
	}

}