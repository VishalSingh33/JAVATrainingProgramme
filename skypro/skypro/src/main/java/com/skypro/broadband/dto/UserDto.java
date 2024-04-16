package com.skypro.broadband.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

private String userId;
private String userType;
private String message;
private String topic;
private String link;
private String readFlag;
private String triggeredBy;

}
