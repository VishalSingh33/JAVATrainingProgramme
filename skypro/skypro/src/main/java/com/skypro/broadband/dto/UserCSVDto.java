package com.skypro.broadband.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserCSVDto {

private String id;
private String userId;
private String userType;
private String message;
private String topic;
private String link;
private String readFlag;
private String triggeredBy;
private LocalDateTime createdDate; 

}
