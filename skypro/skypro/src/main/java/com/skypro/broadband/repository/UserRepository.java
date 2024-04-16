package com.skypro.broadband.repository;

import com.skypro.broadband.entities.User;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    List<User> findAllByIdIn(List<String> id, Pageable pageable);

//   @Query(nativeQuery= true, 
//   value= "select * from entities e where (e.id =:id or e.identity_number =:id)")
//   Optional<Entities> findByIdOrIdentityId(String id);
  
}
