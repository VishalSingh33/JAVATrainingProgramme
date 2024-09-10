package com.order.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDto {

    private String productId;
    private String productName;
    private double MRP;
    private int quantity;
    private double sellingPrice;
    private double total;
    private double discount;
    private double taxableAmount;
    private double cgstRate;
    private double cgstAmount;
    private double sgstRate;
    private double sgstAmount;
    private double totalAmount;

}