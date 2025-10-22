package com.parking.lot.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    private String ticketId;
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parkingSpot;
    private Instant entryTime;
    @ManyToOne
    @JoinColumn(name = "vehicle_number")
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gate_id", nullable = false)
    private Gate gate;

    // Fluent helpers mirroring your Go methods
    public Ticket ForVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Ticket HavingEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public Ticket WithParkingSpot(Parking parkingSpot) {
        this.parkingSpot = parkingSpot;
        return this;
    }

    public Ticket FromGate(Gate gate) {
        this.gate = gate;
        return this;
    }

    public Ticket Build() {
        return this;
    }
}
