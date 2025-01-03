package com.nvr.heartbeat.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Table(name = "heartbeat")
@Entity
public class RegistrationId {

    @Id
    @Column(name = "api_key")
    private String apiKey;
    @Column(name = "client_service_name")
    private String clientServiceName;
    @Column(name = "ttl")
    private long ttl;
    @Column(name = "webhook_url")
    private String webhookUrl;
    
    public RegistrationId(String apiKey, String clientServiceName, long ttl, String webhookUrl) {
        this.apiKey = apiKey;
        this.clientServiceName = clientServiceName;
        this.ttl = ttl;
        this.webhookUrl = webhookUrl;
    }

    public RegistrationId() {}

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getClientServiceName() {
        return clientServiceName;
    }

    public void setClientServiceName(String clientServiceName) {
        this.clientServiceName = clientServiceName;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @Override
    public String toString() {
        return "RegistrationId [apiKey=" + apiKey + ", clientServiceName=" + clientServiceName + ", ttl=" + ttl
                + ", webhookUrl=" + webhookUrl + "]";
    }

}
