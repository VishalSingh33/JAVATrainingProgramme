package com.parking.lot.service;

import com.parking.lot.entity.*;
import com.parking.lot.enums.*;
import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.repository.TicketRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final VehicleService vehicleService;
    private final GateService gateService;
    private final RandomSpotAssignmentStrategyService spotAssignmentStrategy;
    private final TicketRepository ticketRepository;

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, String gateId) {
        // vehicle lookup or register
        Vehicle vehicle = vehicleService.findVehicleById(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("vehicleNumber not found with id: " + vehicleNumber));

        // gate lookup
        Gate gate = gateService.getGate(gateId)
                .orElseThrow(() -> new ParkingLotException("Gate not found: " + gateId));

        // assign spot
        Parking spot = spotAssignmentStrategy.assignSpot(gateId, vehicleType);
        if (spot == null) {
            throw new ParkingLotException("No parking spot available for vehicle type: " + vehicleType);
        }

        // build ticket
        Ticket ticket = new Ticket()
                .setTicketId(UUID.randomUUID().toString())
                .ForVehicle(vehicle)
                .FromGate(gate)
                .HavingEntryTime(Instant.now())
                .WithParkingSpot(spot)
                .Build();

        // simple id + store
        ticketRepository.save(ticket);

        // bind ticket to vehicle for later billing
        // vehicleService.bindTicketToVehicle(vehicleNumber, ticket);

        return ticket;
    }
}
