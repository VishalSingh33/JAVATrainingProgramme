package com.parking.lot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.parking.lot.entity.Parking;
import com.parking.lot.enums.SpotStatus;
import com.parking.lot.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.parking.lot.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Randomly assigns the first available parking spot suitable for the vehicle type.
 */
@Component
@RequiredArgsConstructor
public class RandomSpotAssignmentStrategyService {

    private final ParkingRepository parkingRepository;
    private final Random random = new Random();
    private static final Logger log = LoggerFactory.getLogger(RandomSpotAssignmentStrategyService.class);

    public Parking assignSpot(String gateId, VehicleType vehicleType) {
        // Fetch all available spots compatible with the vehicle type for this gate
        List<Parking> availableSpots = fetchAvailableSpotsForVehicleType(gateId, vehicleType);

        if (availableSpots == null || availableSpots.isEmpty()) {
            log.info("No available spots for {} at gate {}", vehicleType, gateId);
            return null;
        }

        // Random assignment for demo — in production you can use proximity-based or FIFO logic
        Parking assignedSpot = availableSpots.get(random.nextInt(availableSpots.size()));
        assignedSpot.setSpotStatus(SpotStatus.OCCUPIED);
        parkingRepository.save(assignedSpot);

        return assignedSpot;
    }
    /**
     * Mocked method to fetch available spots for demo.
     * Replace with a real repository lookup later.
     */
    private List<Parking> fetchAvailableSpotsForVehicleType(String gateId, VehicleType vehicleType) {
        // Assuming ParkingSpot entity has: gateId, vehicleType, and status fields
        String vehicleTypeStr = vehicleType.name();

        List<Parking> parkList = parkingRepository.findByGateIdAndSpotTypeAndStatus(gateId, vehicleTypeStr);
        return parkList;
    }

}
