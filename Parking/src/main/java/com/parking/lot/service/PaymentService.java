package com.parking.lot.service;

import org.springframework.stereotype.Service;

import com.parking.lot.dto.Payment;
import com.parking.lot.enums.BillStatus;
import com.parking.lot.enums.PaymentMode;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PaymentService {

    private final AtomicInteger idSeq = new AtomicInteger(1);

    public Payment makePayment(PaymentMode mode, double amount) {
        // Stub out actual gateway logic; mark success by default
        return Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .paymentMode(mode)
                .amount(amount)
                .paymentStatus(BillStatus.SUCCESSFUL)
                .build();
    }
}
