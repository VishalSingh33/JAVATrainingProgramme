package com.parking.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.parking.lot.entity.Vehicle;

@EnableJpaRepositories 
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query(nativeQuery = true, value = " Select * from Vehicle where vehicle_number = :vehicleNumber")
    Object findByVehicleNumber(String vehicleNumber);

    

}
