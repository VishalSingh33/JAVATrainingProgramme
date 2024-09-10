package com.order.invoice.dto;

import java.util.*;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    // invoice
    private String orderNumber;
    private String panCard;
    private String gstRegistrationNo;
    private String deliveryPartner;
    //// user
    private String userName;
    private String mobile;
    private String houseNo;
    private String streetAddress;
    private String landmark;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private double discountAmount;
    private double totalTaxableAmount;
    private double totalTaxAmount;
    private double grandTotal;
    private String paymentMode;
    // items
    private List<InvoiceItemDto> invoiceItems;

}