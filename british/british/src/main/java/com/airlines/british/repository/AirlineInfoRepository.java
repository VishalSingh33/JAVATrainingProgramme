package com.airlines.british.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.AirlineInfo;

@Repository
public interface AirlineInfoRepository extends JpaRepository<AirlineInfo, String> {
}
