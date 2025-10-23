package com.onnet.mqtt.mqtt.model;

import java.util.Map;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class MqttModel {

	@NonNull
	private String acknowledgeNo;

	@NonNull
	private String topic;

	@NonNull
	private Boolean retained;

	@NonNull
	private Integer qos;

	private Map<String, Object> mqttMessage;

	public String getAcknowledgeNo() {
		return acknowledgeNo;
	}

	public void setAcknowledgeNo(String acknowledgeNo) {
		this.acknowledgeNo = acknowledgeNo;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Boolean getRetained() {
		return retained;
	}

	public void setRetained(Boolean retained) {
		this.retained = retained;
	}

	public Integer getQos() {
		return qos;
	}

	public void setQos(Integer qos) {
		this.qos = qos;
	}

	public Map<String, Object> getMqttMessage() {
		return mqttMessage;
	}

	public void setMqttMessage(Map<String, Object> mqttMessage) {
		this.mqttMessage = mqttMessage;
	}

}