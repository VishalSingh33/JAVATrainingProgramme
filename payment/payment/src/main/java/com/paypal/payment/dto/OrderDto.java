package com.paypal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Double totalAmount;
    private String userId;
    private String passengerId;
    private String bookingId;
    private String fareId;

}
