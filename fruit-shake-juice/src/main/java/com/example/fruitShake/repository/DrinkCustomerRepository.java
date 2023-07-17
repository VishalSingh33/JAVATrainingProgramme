package com.example.fruitShake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fruitShake.entities.DrinkCustomer;
import com.example.fruitShake.entities.Users;

@Repository
public interface DrinkCustomerRepository extends JpaRepository<DrinkCustomer, String> {

//    @Query("SELECT * FROM customer"
//            + " WHERE (:cName IS NULL OR name ILIKE CONCAT('%',:cName,'%'))"
//            + " AND (:cPhoneNo IS NULL OR status=:cPhoneNo)")
//    DrinkCustomer findAll(@Param("bookId") String cName, @Param("mobileNo") String cPhoneNo);

}
