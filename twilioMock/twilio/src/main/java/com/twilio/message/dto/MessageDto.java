package com.twilio.message.dto;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    
    @JsonProperty("destinationPhoneNumber")
    @NotBlank(message = "Phone Number cannot be blank")
    public final String whatsappTo;

    @JsonProperty("message")
    @NotBlank(message = "Message cannot be blank")
    private final String message;
    
}
