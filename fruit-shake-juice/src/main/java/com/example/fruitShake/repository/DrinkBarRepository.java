package com.example.fruitShake.repository;

import java.util.List;
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

//    @Query("SELECT * FROM drinks d"
//            + " WHERE (:name IS NULL OR d.name LIKE CONCAT('%',:name,'%'))"
//            + " AND (:status IS NULL OR d.status=:status)")
//    List<DrinkBar> findAll(@Param("name") String name, @Param("status") String status);

//     @Query("UPDATE books SET status='INACTIVE', updated_date=NOW(), "
//            + " updated_by = :curUserId WHERE id = :id ")
//     ResponseEntity<Optional<Books>> getUnblockId(String bookId);

}
