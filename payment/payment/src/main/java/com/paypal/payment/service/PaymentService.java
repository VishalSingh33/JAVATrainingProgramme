package com.paypal.payment.service;

import java.net.URI;

import com.paypal.payment.dto.OrderDto;
import com.paypal.payment.entites.CreatedOrder;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    CreatedOrder createOrder(Double totalAmount, URI returnUrl, OrderDto orderDto);

    void captureOrder(String orderId);

    // PayPalPayment createOrderPal(OrderDto orderDto, URI returnUrl) throws IOException;

}