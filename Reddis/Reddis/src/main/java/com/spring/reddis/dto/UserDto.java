package com.spring.reddis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
// import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String fullName;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false)
    private long mobileNumber;

    @Column(name = "gender", nullable = false)
    private Gender gender;

}