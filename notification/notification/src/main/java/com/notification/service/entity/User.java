package com.notification.service.entity;

import javax.validation.constraints.Pattern;

import com.notification.service.dto.DeviceType;
import com.notification.service.dto.Plan;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User 
// implements Serializable 
{

	// private static final long serialVersionUID = -9182526415261141967L;

	@Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

	@Column(name = "name", nullable = false)
    private String fullName;

	@Pattern(regexp = "(^[\\w!#$%&’*+/=?`{|}~^-]+(?:.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+([a-zA-Z]{2,6})$)*", message = "Please enter valid emailId")
    @Column(name = "email", nullable = false)
    private String email;

    // @Pattern(regexp = "^([9876]{1})(\\d{9})", message = "Please enter valid mobile number")
    @Column(name = "mobile", nullable = false)
    private long phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "subscriptionplan")
	private Plan subscriptionPlan;

	@Column(name = "notificationlimit")
	private String notificationLimit;

	@Enumerated(EnumType.STRING)
	@Column(name = "devicetype")
	private DeviceType deviceType;
	
}