package com.parking.lot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.Instant;

import com.parking.lot.dto.Payment;
import com.parking.lot.enums.BillStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    private String billingId;
    private Instant exitTime;
    private BillStatus billStatus;

    @OneToOne(optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gate_id", nullable = false)
    private Gate gate;

    @Embedded
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    // Fluent helpers mirroring your Go methods
    public Bill HavingTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public Bill WithPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public Bill AtGate(Gate gate) {
        this.gate = gate;
        return this;
    }

    public Bill Generate(BillStatus status) {
        this.billStatus = status;
        this.exitTime = Instant.now();
        return this;
    }
}
