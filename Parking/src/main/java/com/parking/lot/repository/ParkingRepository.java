package com.parking.lot.repository;

import java.util.List;
import java.util.Optional;

import com.parking.lot.entity.Parking;
import com.parking.lot.enums.SpotStatus;
import com.parking.lot.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.parking.lot.entity.Ticket;

@EnableJpaRepositories
@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

    @Query(nativeQuery = true, value = " SELECT * FROM Parking WHERE gate_id = :gateId AND vehicle_type = :vehicleType AND spot_status = 'AVAILABLE' ")
    List<Parking> findByGateIdAndSpotTypeAndStatus(String gateId, String vehicleType);
}


