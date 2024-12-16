package com.nvr.heartbeat.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String email;
    
    private String password;
    
    private String fullName;

    public RegisterUserDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String setFullName(String fullName) {
        return this.fullName = fullName;
    }
   
}