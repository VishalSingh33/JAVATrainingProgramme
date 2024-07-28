package com.twilio.message.config;

// import com.clicksend.sdk.ApiClient;
// import com.clicksend.sdk.api.SmsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ClickSend.ApiClient;
import ClickSend.Api.SmsApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClickSendConfig {

    @Value("${clicksend.username}")
    private String username;

    // @Value("${clicksend.apiKey}")
    // private String apiKey;

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        // Configure the ApiClient if necessary
        apiClient.setUsername(username);
        // apiClient.setPassword(apiKey);
        return apiClient;
    }

    @Bean
    public SmsApi smsApi(ApiClient apiClient) {
        return new SmsApi(apiClient);
    }
}

