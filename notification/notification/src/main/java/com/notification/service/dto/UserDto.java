package com.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String fullName;

    private String email;

	private long phoneNumber;

	private Plan subscriptionPlan;

	private String notificationLimit;

	private DeviceType deviceType;

}