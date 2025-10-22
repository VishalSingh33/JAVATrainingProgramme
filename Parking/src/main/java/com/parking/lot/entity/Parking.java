package com.parking.lot.entity;

import com.parking.lot.enums.SpotStatus;
import com.parking.lot.enums.SpotType;
import com.parking.lot.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @Column(name = "parking_id")
    private String id; // rename to `id` to avoid PK name mismatches

    // One spot belongs to a floor
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false, referencedColumnName = "floor_id")
    private ParkingFloor floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "spot_status", nullable = false)
    private SpotStatus spotStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "spot_type", nullable = false)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    // Gate through which this spot is accessed (if that’s your design)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "gate_id", nullable = false, referencedColumnName = "id")
    private Gate gate;
}
