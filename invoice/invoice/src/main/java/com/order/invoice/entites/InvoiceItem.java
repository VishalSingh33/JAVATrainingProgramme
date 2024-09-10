package com.order.invoice.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    
}
