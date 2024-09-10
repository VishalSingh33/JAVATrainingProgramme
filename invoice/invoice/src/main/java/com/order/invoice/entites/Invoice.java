package com.order.invoice.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id", nullable = false, unique = true)
    private String invoiceId;

    @Column(name = "order_no", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "pan")
    private String panCard;

    @Column(name = "gst_reg")
    private String gstRegistrationNo;

    @Column(name = "delivery_partner")
    private String deliveryPartner;

    @Embedded
    private User user;

    @Column(name = "discount")
    private double discountAmount;

    @Column(name = "taxable_amount") 
    private double totalTaxableAmount;

    @Column(name = "tax_amount")
    private double totalTaxAmount;

    @Column(name = "grand_total", nullable = false)
    private double grandTotal;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "date", nullable = false)
    private LocalDateTime invoiceDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems;

}
