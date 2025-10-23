package com.onnet.mqtt.mqtt.service;

import java.util.Map;
import java.util.UUID;
import java.util.LinkedHashMap;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onnet.mqtt.helper.Response;
import com.onnet.mqtt.helper.ResponseCode;
import com.onnet.mqtt.helper.ResponseHelper;
import com.onnet.mqtt.mqtt.model.MqttModel;

@Service
public class MqttPublishService {

	@Value("${mqtt.publisherId}")
	private String mqttPublisherId;

	@Value("${mqtt.serverAddress}")
	private String mqttServerAddress;

	private static IMqttClient instance;

	private ObjectMapper mapper = new ObjectMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	Response<?> response = null;
	private final Map<String, CompletableFuture<Response<?>>> responseMap = new ConcurrentHashMap<>();

	public Response<?> publishMessageStreaming(String req, BindingResult bindingResult)
			throws InterruptedException, ExecutionException, MqttException, JsonMappingException,
			JsonProcessingException, InterruptedException, ExecutionException {

		logger.debug("MQTT_MSC_001 - Begin - reqParams:{}" + req);

		Response<?> response = null;
		MqttModel messagePublishModel = null;
		CompletableFuture<Response<?>> futureResponse = new CompletableFuture<>();
		try {
			if (bindingResult.hasErrors()) {
				logger.error("MQTT_MSC_001 - Error - binding result has some errors");
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, "Some parameters invalid");
			}

			messagePublishModel = mapper.readValue(req, MqttModel.class);
			// Create a map to hold the message content
			Map<String, Object> messageContent = new LinkedHashMap<>();

			String acknowledgeNo = UUID.randomUUID().toString();
			messageContent.put("topic", messagePublishModel.getTopic());
			messageContent.put("qos", messagePublishModel.getQos());
			// messageContent.put("message", messagePublishModel.getMessage());
			messageContent.put("mqttMessage", messagePublishModel.getMqttMessage());

			// only this should be in payload
			Map<String, Object> mqttMessageParams = messagePublishModel.getMqttMessage();
			mqttMessageParams.put("acknowledgeNo", acknowledgeNo);

			// Convert the message content to JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = null;

			logger.info("MQTT_MSC_001 - jsonPayload:{}" + mqttMessageParams);
			jsonPayload = objectMapper.writeValueAsString(mqttMessageParams);

			// Create an MQTT message with the JSON payload
			MqttMessage mqttMessage = new MqttMessage(jsonPayload.getBytes());
			logger.info("MQTT_MSC_001 - mqtt message to be published:" + mqttMessage);
			responseMap.put(acknowledgeNo, futureResponse); // mapping to Global map(CompletableFuture)

			// Connect & Publish the message to the MQTT broker
			Response<?> mqttBrokerResponseInfo = getInstanceClient(); // calling service layer
			logger.debug("MQTT_MSC_001 - mqttBrokerResponseInfo:{}" + mqttBrokerResponseInfo);

			if (mqttBrokerResponseInfo == null) {
				logger.error(
						"MQTT_MSC_001 - Error - API response for message publishing to the MQTT broker failed - is returning null");
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured);
			}

			if (mqttBrokerResponseInfo.getStatusCode() != 200) {
				String errorMessage = mqttBrokerResponseInfo.getMessage();
				logger.error(
						"MQTT_MSC_001 - Error - API response for message publishing to the MQTT broker is giving Failed status - "
								+ errorMessage);
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, errorMessage);
			}

			String mqttBrokerResponseMessage = mqttBrokerResponseInfo.getMessage();
			logger.info("MQTT_MSC_001 - mqttBrokerResponseMessage - " + mqttBrokerResponseMessage);

			// Publish the message to the MQTT broker
			IMqttClient mqttBrokerInfo = (IMqttClient) mqttBrokerResponseInfo.getData();

			if (mqttBrokerInfo == null) {
				logger.error("MQTT_MSC_001 - Publishing Message to the MQTT broker failed - mqttBrokerInfo is null");
				return ResponseHelper.getErrorResponse(ResponseCode.Error_occured,
						"message publishing to the MQTT broker failed");
			}

			logger.info("MQTT_MSC_001 - trying to publish message with topic - " + messagePublishModel.getTopic()
					+ ", message:{}" + mqttMessage);
			mqttBrokerInfo.publish(messagePublishModel.getTopic(), mqttMessage);// topic, message are passed/published
			logger.info("MQTT_MSC_001 -  Message Published");

			// Return the message content (optional)
			logger.info("MQTT_MSC_001 - End - response:{}" + messageContent);
			response = ResponseHelper.getSuccessResponse(messageContent);

		} catch (JsonProcessingException e) {
			logger.error("MQTT_MSC_001 - error - " + e.getMessage());
			response = ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND,
					"An error occurred while converting string to json");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MQTT_MSC_001 - error - " + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND, e.getMessage());
		}

		try {
			Response<?> finalResponse = futureResponse.get(19, TimeUnit.SECONDS); // Wait for 10 seconds
			response = ResponseHelper.getSuccessResponse(finalResponse);
			return response; // Return the response from API3 back to Postman
		} catch (TimeoutException e) {
			return ResponseHelper.getErrorResponse(ResponseCode.REQUEST_TIMEOUT, "Request read Timeout");
		} finally {
			logger.debug("MQTT_MSC_001 - End - response:{}" + response);
		}

	}

	public void getFutureResponse(Response<?> reqParams) {
		for (Map.Entry<String, CompletableFuture<Response<?>>> entry : responseMap.entrySet()) {
			CompletableFuture<Response<?>> futureResponse = entry.getValue();
			if (futureResponse != null) {
				futureResponse.complete(reqParams);
			} else {
				logger.error("MQTT_MSC_001 - No CompletableFuture : {}", "Null Value");
			}
		}
	}

	public Response<?> getInstanceClient() {
		logger.debug("MQTT_MSS_001 - Begin");

		try {
			// Check if the MQTT client instance is null
			if (instance == null) {
				logger.info("MQTT_MSS_001 - mqttServerAddress - " + mqttServerAddress + ", mqttPublisherId - "
						+ mqttPublisherId);
				if (mqttServerAddress == null || mqttPublisherId == null) {
					logger.error("MQTT_MSS_001 - MQTT server address or publisher ID is null.");
					return ResponseHelper.getErrorResponse(ResponseCode.Error_occured,
							"MQTT server address or publisher ID is null");
				}
				// Initialize MQTT client -> provided serverAddress & publisher/clientID
				instance = new MqttClient(mqttServerAddress, mqttPublisherId);
			}
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);// Set the option to automatically reconnect if the connection is lost
			options.setCleanSession(true); // Set the option to use a clean session (no state is kept between sessions)
			options.setConnectionTimeout(180); // Set the connection timeout to 180 seconds
			options.setKeepAliveInterval(180); // 2 

			// Check if the MQTT client is not already connected
			if (!instance.isConnected()) {
				logger.info("MQTT_MSS_001 - Attempting to connect to MQTT broker at " + mqttServerAddress
						+ " with publisher ID " + mqttPublisherId);
				logger.info("MQTT_MSS_001  - Connecting to MQTT broker...");
				instance.connect(options);// connection to the MQTT broker using the provided connection options
				logger.info("MQTT_MSS_001  - Connected to MQTT broker...");
			}
			response = ResponseHelper.getSuccessResponse(instance);
		} catch (MqttException e) {
			e.printStackTrace();
			logger.error("MQTT_MSS_001 - An error occurred while connecting to MQTT broker.." + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MQTT_MSS_001 - An error occurred while connecting to MQTT broker.." + e.getMessage());
			return ResponseHelper.getErrorResponse(ResponseCode.Error_occured, e.getMessage());
		}
		logger.debug("MQTT_MSS_001 - End - response:{}" + response);
		return response;
	}

	public Response<?> publishedMessageAck(Response<?> reqParams) {

		Response<?> response = null;
		try {
			Object content = reqParams.getData(); // Assuming getData returns an Object or String
			logger.debug("MQTT_MSC_001 - End - content:{}" + content);

			getFutureResponse(reqParams);
			response = ResponseHelper.getSuccesResponse(content);

		} catch (Exception e) {
			response = ResponseHelper.getErrorResponse(ResponseCode.DATA_NOT_FOUND, e.getMessage());
			logger.debug("MQTT_MSC_001 - End - response:{}" + response);
		}
		logger.debug("MQTT_MSC_001 - End - response:{}" + response);
		return response;
	}

}