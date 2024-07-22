package com.paypal.payment.service;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.paypal.payment.dto.OrderDto;
import com.paypal.payment.dto.PaymentStatus;
import com.paypal.payment.entites.BookingRecord;
import com.paypal.payment.entites.CreatedOrder;
import com.paypal.payment.entites.PayPalPayment;
import com.paypal.payment.repository.BookingRecordRepository;
import com.paypal.payment.repository.PaypalRepository;
import jakarta.servlet.http.HttpServletRequest;
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

// @AllArgsConstructor
@Service
@Slf4j
public class ManagePaymentService implements PaymentService {

    private final PaypalRepository paypalRepository;
    private final BookingRecordRepository bookingRepository;
    // private final PaypalConfig payPalHttpClient ;

    private final String APPROVE_LINK_REL = "approve";
    private final PayPalHttpClient payPalHttpClient;

    public ManagePaymentService(@Value("${paypal.clientId}") String clientId,
            @Value("${paypal.clientSecret}") String clientSecret,
            BookingRecordRepository bookingRepository, PaypalRepository paypalRepository) {

        payPalHttpClient = new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));
        this.paypalRepository = paypalRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @SneakyThrows
    public CreatedOrder createOrder(Double totalAmount, URI returnUrl, OrderDto orderDto) {
        final OrderRequest orderRequest = createOrderRequest(totalAmount, returnUrl);
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLink(order);

        // saveTransactionDetails(orderDto.getFareId(), null, "order", 
        //     null, null, "${paypal.clientId}",
        //     "${paypal.clientSecret}", order.id(), URI.create(approveUri.href()),
        //     orderDto.getBookingId(), orderDto.getUserId(), orderDto.getPassengerId(),  
        //     null, LocalDateTime.now(), LocalDateTime.now());

        return new CreatedOrder(order.id(), URI.create(approveUri.href()));

    }

    @Override
    @SneakyThrows
    public void captureOrder(String orderId) {
        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);

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

    // @Override
    // @SneakyThrows
    // public PayPalPayment createOrderPal(OrderDto orderDto, URI returnUrl) {

    //     final OrderRequest orderRequest = createOrderRequest(orderDto.getTotalAmount(), returnUrl);
    //     final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
    //     try {
    //         final HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
    //         final Order order = orderHttpResponse.result();
    //         LinkDescription approveUri = extractApprovalLink(order);

    //         saveTransactionDetails(orderDto.getFareId(), null, "order", 
    //         null, null, "${paypal.clientId}",
    //         "${paypal.clientSecret}", order.id(), URI.create(approveUri.href()),
    //         orderDto.getBookingId(), orderDto.getUserId(), orderDto.getPassengerId(),  
    //         null, LocalDateTime.now(), LocalDateTime.now());

    //         return new PayPalPayment(orderDto.getFareId(), null, "order",
    //                 null, null, "${paypal.clientId}",
    //                 "${paypal.clientSecret}", order.id(), URI.create(approveUri.href()),
    //                 orderDto.getBookingId(), orderDto.getUserId(), orderDto.getPassengerId(), 
    //                 null, LocalDateTime.now(), LocalDateTime.now());
    //     } catch (Exception e) {
    //         // Proper exception handling depending on your application's requirements
    //         throw new RuntimeException("Failed to create PayPal order", e);
    //     }
    // }

    @SuppressWarnings("unused")
    private void saveTransactionDetails(String paymentId, String transactionId,
            String transactionType, String transactionMethod, String AccountId, String clientId,
            String clientSecret, String orderId, URI approvalLink, String bookingId,
            String userId, String passengerId, PaymentStatus paymentStatus, LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        PayPalPayment payment = new PayPalPayment();

        payment.setPaymentId(paymentId);
        payment.setTransactionId(transactionId);
        payment.setTransactionType(transactionType);
        payment.setTransactionMethod(transactionMethod);
        payment.setAccountId(AccountId);
        payment.setOrderId(orderId);
        payment.setApprovalLink(approvalLink);
        payment.setPaymentStatus(paymentStatus);
        payment.setClientId(clientId);
        payment.setClientSecret(clientSecret);
        payment.setUserId(userId);
        payment.setPassengerId(passengerId);
        payment.setBookingId(bookingId);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        paypalRepository.save(payment);

    }

}
