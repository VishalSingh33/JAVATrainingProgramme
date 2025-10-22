package com.parking.lot.repository;

import com.parking.lot.entity.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, String> {

}


