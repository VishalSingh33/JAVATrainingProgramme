package com.paypal.payment.service;

import java.net.URI;

import com.paypal.payment.entites.CreatedOrder;
public interface PaymentService {

    CreatedOrder createOrder(Double totalAmount, URI returnUrl);

    void captureOrder(String orderId);

}