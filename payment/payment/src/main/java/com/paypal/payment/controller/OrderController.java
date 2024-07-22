package com.paypal.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.payment.dto.OrderDto;
import com.paypal.payment.entites.BookingRecord;
import com.paypal.payment.entites.CreatedOrder;
import com.paypal.payment.entites.Fare;
import com.paypal.payment.entites.Passenger;
import com.paypal.payment.entites.PayPalPayment;
import com.paypal.payment.entites.User;
import com.paypal.payment.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin("*")
@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final PaymentService paymentService;

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    private String orderId = "access_token$sandbox$29d475krxcrg3z2n$0aa956a870bc4b695d1b30fff93230e9";

    @GetMapping
    public String orderPage(Model model) {
        model.addAttribute("orderId", orderId);
        return "order";
    }

    @GetMapping("/capture")
    public String captureOrder(@RequestParam String token) {
        // FIXME(Never Do this either put it in proper scope or in DB)
        orderId = token;
        paymentService.captureOrder(token);
        return "redirect:/orders";
    }

    @PostMapping(value = "/payment")
    public String placeOrder(@RequestParam Double totalAmount, HttpServletRequest request, @RequestBody OrderDto orderDto) {
        final URI returnUrl = buildReturnUrl(request);
        CreatedOrder createdOrder = paymentService.createOrder(totalAmount, returnUrl, orderDto);
        return "redirect:" + createdOrder.getApprovalLink();
    }

    // @PostMapping(value = "/booking")
    // public String palOrders(@RequestBody OrderDto orderDto, HttpServletRequest request) throws IOException {

    //     final URI returnUrl = buildReturnUrl(request);
    //     PayPalPayment palPayment = paymentService.createOrderPal(orderDto, returnUrl);
    //     return "redirect:" + palPayment.getApprovalLink();
    // }

    private URI buildReturnUrl(HttpServletRequest request) {
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/orders/capture",
                    null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

// https://youtube.com/shorts/WnJ7YjeB4sk?si=IySb1WWW2AWMAjYy
// que bien sabe ser de real madrid