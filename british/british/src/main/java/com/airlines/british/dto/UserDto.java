package com.airlines.british.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String fullName;

    @Column(name = "username", nullable = false)
    private String userName;

    @Pattern(regexp = "(^[\\w!#$%&’*+/=?`{|}~^-]+(?:.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+([a-zA-Z]{2,6})$)*", message = "Please enter valid emailId")
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^([9876]{1})(\\d{9})", message = "Please enter valid mobile number")
    @Column(name = "mobile", nullable = false)
    private long mobileNumber;

    @Column(name = "gender", nullable = false)
    private Gender gender;

}