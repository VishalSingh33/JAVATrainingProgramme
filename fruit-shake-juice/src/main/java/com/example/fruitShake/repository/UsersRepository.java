package com.example.fruitShake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.fruitShake.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

//    @Query("SELECT * FROM users"
//            + " WHERE (:userName IS NULL OR user_name ILIKE CONCAT('%',:userName,'%'))"
//            + " AND (:mobileNo IS NULL OR status=:mobileNo)")
//    Users findAll(@Param("bookId") String userName, @Param("mobileNo") String mobileNo);

}
