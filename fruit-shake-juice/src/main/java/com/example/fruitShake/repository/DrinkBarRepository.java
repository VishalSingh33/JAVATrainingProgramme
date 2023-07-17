package com.example.fruitShake.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.fruitShake.entities.Books;
import com.example.fruitShake.entities.DrinkBar;

@Repository
public interface DrinkBarRepository extends JpaRepository<DrinkBar, String> {

//    @Query("SELECT * FROM drinks"
//            + " WHERE (:dName IS NULL OR name ILIKE CONCAT('%',:dName,'%'))"
//            + " AND (:status IS NULL OR status=:status)")
//    DrinkBar findAll(@Param("dID") String dID, @Param("status") String status);

    // @Query("UPDATE books SET status='INACTIVE', updated_date=NOW(), updated_by =
    // :curUserId WHERE id = :id")
    // ResponseEntity<Optional<Books>> getUnblockId(String bookId);

}
