package com.example.fruitShake.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.fruitShake.entities.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, String> {

//    @Query("SELECT * FROM books"
//            + " WHERE (:bookName IS NULL OR book_name ILIKE CONCAT('%',:bookName,'%'))"
//            + " AND (:status IS NULL OR status=:status)")
//    Books findAll(@Param("bookId") String bookId, @Param("status") String status);


    // @Query("UPDATE books SET status='INACTIVE', updated_date=NOW(), updated_by = :curUserId WHERE id = :id")
    // ResponseEntity<Optional<Books>> getUnblockId(String bookId);

}
