package com.order.invoice.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // private String userId;
    private String userName;
    private String mobile;
    private String houseNo;
    private String streetAddress;
    private String landmark;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    
}
