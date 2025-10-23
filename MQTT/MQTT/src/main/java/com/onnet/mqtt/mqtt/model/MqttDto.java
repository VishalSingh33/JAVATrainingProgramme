package com.onnet.mqtt.mqtt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class MqttDto {

    private Object mqttMessage;

    public Object getMqttMessage() {
        return mqttMessage;
    }

    public void setMqttMessage(Object mqttMessage) {
        this.mqttMessage = mqttMessage;
    }

}

// .\mosquitto_pub.exe -h 106.51.64.251 -p 11883 -t topic1 -m "h"
// .\mosquitto_sub.exe -h 106.51.64.251 -p 11883 -t topic1    
// .\mosquitto_sub.exe -h 103.81.156.251 -p 1883 -t 9c:5a:44:1c:ba:e4
