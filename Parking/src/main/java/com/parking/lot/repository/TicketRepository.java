package com.parking.lot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.parking.lot.entity.Ticket;

@EnableJpaRepositories
@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(nativeQuery = true, value = " Select * from Ticket where vehicle_number = :vehicleNumber")
    Optional<List<Ticket>> findByIdVechileNumber(String vehicleNumber);

}
