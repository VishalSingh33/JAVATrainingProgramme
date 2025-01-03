package com.nvr.heartbeat.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RegistrationIdDto {

    private String apiKey;
    private String deviceId;
    private String clientServiceName;

    public RegistrationIdDto(String apiKey, String deviceId, String clientServiceName) {
        this.apiKey = apiKey;
        this.deviceId = deviceId;
        this.clientServiceName = clientServiceName;
    }

}
