package com.parking.lot.service;

import com.parking.lot.dto.Payment;
import com.parking.lot.entity.*;
import com.parking.lot.enums.*;
import com.parking.lot.repository.BillRepository;
import com.parking.lot.repository.TicketRepository;
import com.parking.lot.exception.*;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService {

    private final PaymentService paymentService;
    private final GateService gateService;
    private final BillRepository billRepository;
    private final TicketRepository ticketRepository;

    public Bill generateBill(String vehicleNumber, String gateId) {

        // 1) find ticket associated with vehicle
        List<Ticket> ticketList = ticketRepository.findByIdVechileNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("vehicleNumber not found with id: " + vehicleNumber));

        Ticket ticket = ticketList.stream()
                .max(Comparator.comparing(Ticket::getEntryTime)) // or getExitTime if needed
                .orElseThrow(() -> new RuntimeException("No valid timestamp found for vehicle: " + vehicleNumber));
        // 2) gate details
        Gate gate = gateService.getGate(gateId)
                .orElseThrow(() -> new ParkingLotException("Gate not found: " + gateId));

        // 3) payment
        Payment payment = paymentService.makePayment(PaymentMode.CREDIT_CARD, 100.0);

        // 4) build bill
        Bill bill = new Bill()
                .setBillingId(UUID.randomUUID().toString())
                .HavingTicket(ticket)
                .AtGate(gate)
                .WithPayment(payment)
                .Generate(BillStatus.SUCCESSFUL);

        // 5) (optional) persist in-memory
        billRepository.save(bill);
        return bill;
    }
}
