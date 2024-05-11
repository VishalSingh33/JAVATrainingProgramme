package com.paypal.payment.service;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.paypal.payment.config.PaypalConfig;
import com.paypal.payment.dto.PaymentStatus;
import com.paypal.payment.entites.CreatedOrder;
import com.paypal.payment.entites.Passenger;
import com.paypal.payment.entites.PayPalPayment;
import com.paypal.payment.repository.PaypalRepository;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
@Slf4j
public class ManagePaymentService implements PaymentService {

    private final PaypalRepository paypalRepository;

    private final String APPROVE_LINK_REL = "approve";
    // private final PaypalConfig payPalHttpClient ;
    private final PayPalHttpClient payPalHttpClient;

    public ManagePaymentService(@Value("${paypal.clientId}") String clientId,
            @Value("${paypal.clientSecret}") String clientSecret, PaypalRepository paypalRepository) {

        payPalHttpClient = new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));
        this.paypalRepository = paypalRepository;
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrder(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = createOrderRequest(totalAmount, returnUrl);
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLink(order);

        // saveTransactionDetails(order.id(), "Order", "Create", clientId, clientSecret);

        return new CreatedOrder(order.id(), URI.create(approveUri.href()));

    }

    @Override
    @SneakyThrows
    public void captureOrder(String orderId) {
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);

        // saveTransactionDetails(orderId, "Order", "Capture", clientId, clientSecret);

        log.info("Order Capture Status: {}", httpResponse.result().status());
    }

    private OrderRequest createOrderRequest(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = new OrderRequest();
        setCheckoutIntent(orderRequest);
        setPurchaseUnits(totalAmount, orderRequest);
        setApplicationContext(returnUrl, orderRequest);
        return orderRequest;
    }

    private OrderRequest setApplicationContext(URI returnUrl, OrderRequest orderRequest) {
        return orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUrl.toString()));
    }

    private void setPurchaseUnits(Double totalAmount, OrderRequest orderRequest) {
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()));
        orderRequest.purchaseUnits(Arrays.asList(purchaseUnitRequest));
    }

    private void setCheckoutIntent(OrderRequest orderRequest) {
        orderRequest.checkoutPaymentIntent("CAPTURE");
    }

    private LinkDescription extractApprovalLink(Order order) {
        LinkDescription approveUri = order.links().stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return approveUri;
    }

    @SuppressWarnings("unused")
    private void saveTransactionDetails(String txnId, String type, String method, String clientId, String secretKey) {

        PayPalPayment payment = new PayPalPayment();

        payment.setPaymentId(null);
        payment.setTransactionId(null);
        payment.setTransactionType(null);
        payment.setTransactionMethod(null);
        payment.setAccountId(null);
        payment.setApprovalLink(null);
        payment.setPaymentStatus(null);
        payment.setClientId("${paypal.clientId}");
        payment.setClientSecret("${paypal.clientSecret}");

        // payment.setUserId(passenger.getUser().getUserId());
        // payment.setPassengerId(passenger.getPassengerId());
        // payment.setBookingId(booking.getBookingId());
        // payment.setFareId(fare.getFareId());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        paypalRepository.save(payment);

    }

}
