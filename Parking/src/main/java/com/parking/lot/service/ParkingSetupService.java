package com.parking.lot.service;

import com.parking.lot.dto.ParkingSetupDto;
import com.parking.lot.entity.Gate;
import com.parking.lot.entity.Parking;
import com.parking.lot.entity.ParkingFloor;
import com.parking.lot.entity.Vehicle;
import com.parking.lot.enums.GateType;
import com.parking.lot.enums.SpotStatus;
import com.parking.lot.enums.SpotType;
import com.parking.lot.enums.VehicleType;
import com.parking.lot.repository.GateRepository;
import com.parking.lot.repository.ParkingFloorRepository;
import com.parking.lot.repository.ParkingRepository;
import com.parking.lot.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParkingSetupService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingFloorRepository parkingFloorRepository;
    private final ParkingRepository parkingRepository;

    @Transactional
    public void createParkingSetup(ParkingSetupDto request) {

        // 1️⃣ Create Gate
        Gate gate = Gate.builder()
                .id(request.getGateId())
                .gateType(GateType.valueOf(request.getGateType()))
                .gateNumber(request.getGateNumber())
                .build();
        gateRepository.save(gate);

        // 2️⃣ Create Vehicle
        Vehicle vehicle = Vehicle.builder()
                .vehicleNumber(request.getVehicleNumber())
                .vehicleType(VehicleType.valueOf(request.getVehicleType()))
                .build();
        vehicleRepository.save(vehicle);

        // 3️⃣ Create Floor
        ParkingFloor floor = ParkingFloor.builder()
                .id(request.getFloorId())
                .levelNumber(request.getLevelNumber())
                .build();
        parkingFloorRepository.save(floor);

        // 4️⃣ Create Parking Spot
        Parking parking = Parking.builder()
                .id(request.getParkingId())
                .gate(gate)
                .floor(floor)
                .spotType(SpotType.valueOf(request.getSpotType()))
                .spotStatus(SpotStatus.valueOf(request.getSpotStatus()))
                .vehicleType(VehicleType.valueOf(request.getVehicleType()))
                .build();
        parkingRepository.save(parking);
    }
}
