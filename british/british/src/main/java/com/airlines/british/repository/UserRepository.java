package com.airlines.british.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.airlines.british.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // @Cacheable(value = "commonDetails")
    // public CommonDetailsDto getCommonDetails(String token, List<String> keys)  {
    //     // your DB or API call should be here
    // }

}
