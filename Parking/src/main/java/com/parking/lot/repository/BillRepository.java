package com.parking.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.parking.lot.entity.Bill;

@EnableJpaRepositories 
@Repository
public interface BillRepository extends JpaRepository<Bill, String> {

   
}
