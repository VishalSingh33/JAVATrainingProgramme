package com.parking.lot.service;

import com.parking.lot.entity.*;
import com.parking.lot.enums.*;
import com.parking.lot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Optional<Vehicle> findVehicleById(String vehicleNumber) {
        return vehicleRepository.findById(vehicleNumber);
    }

    public Vehicle registerVehicle(String vehicleNumber, VehicleType type) {
        Vehicle vec = Vehicle.builder()
                .vehicleNumber(vehicleNumber)
                .vehicleType(type)
                .build();
        vehicleRepository.save(vec);
        return vec;
    }

    public Optional<Object> findTicketAssociatedWithVehicle(String vehicleNumber) {
        return Optional.ofNullable(vehicleRepository.findByVehicleNumber(vehicleNumber));
    }


}
