package com.parking.lot.dto;

import com.parking.lot.enums.BillStatus;
import com.parking.lot.enums.PaymentMode;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Accessors(chain = true)
@Builder
public class Payment {
    
    private String paymentId;
    private PaymentMode paymentMode;
    private double amount;

    // Your Go model uses BillStatus for paymentStatus
    private BillStatus paymentStatus;
}
