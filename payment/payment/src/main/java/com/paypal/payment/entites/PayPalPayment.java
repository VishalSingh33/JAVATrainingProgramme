package com.paypal.payment.entites;

import java.net.URI;
import java.time.LocalDateTime;

import com.paypal.payment.dto.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PayPalPayment {

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String paymentId;

    @Column
    private String transactionId;

    @Column
    private String transactionType;
    @Column
    private String transactionMethod; // what Type or Method ?

    @Column
    private String AccountId; // what account ?

    @Column
    private String clientId; // clientId or accountId

    @Column
    private String clientSecret;

    @Column
    private URI approvalLink;

    @Column
	private String bookingId; // orderId

    @Column
    private String userId;
    
    @Column
	private String passengerId;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentStatus paymentStatus;

    // @Column
    // private String currency; enum typre or String 

    @Column
	private String fareId; // can fare entity be replaced with this entity

    @Column(name = "created", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updatedAt;


    
}
