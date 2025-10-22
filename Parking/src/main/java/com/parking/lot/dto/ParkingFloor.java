package com.parking.lot.dto;

import lombok.*;
import lombok.experimental.Accessors;
import java.util.*;

import com.parking.lot.enums.SpotStatus;
import com.parking.lot.enums.SpotType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class ParkingFloor {

    private String floorId;

    // Map<SpotType, List<ParkingSpot>>
//    @Builder.Default
//    private Map<SpotType, List<ParkingSpot>> parkingSpots = new EnumMap<>(SpotType.class);

    private int availableSpots;
    private SpotStatus status;
}
