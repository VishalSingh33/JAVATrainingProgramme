package com.parking.lot.dto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

import com.parking.lot.entity.Gate;
import com.parking.lot.enums.SpotStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
@Embeddable
public class ParkingLot {

    private String parkinglotId;

    @Builder.Default
    private List<ParkingFloor> parkingFloors = new ArrayList<>();

    @Builder.Default
    private List<Gate> gates = new ArrayList<>();

    private SpotStatus status;
    private String name;
    private String address;
}
