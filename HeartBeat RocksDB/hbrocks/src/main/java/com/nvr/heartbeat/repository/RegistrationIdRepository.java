package com.nvr.heartbeat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nvr.heartbeat.entites.RegistrationId;

@Repository
public interface RegistrationIdRepository extends JpaRepository<RegistrationId, String> {

    @Query(nativeQuery = true, value = " SELECT * FROM heartbeat WHERE "
            + " api_key = :apiKey AND client_service_name = :clientServiceName ")
    RegistrationId heartbeatVerification(@Param("apiKey") String apiKey, @Param("clientServiceName") String clientServiceName);

    @Query(nativeQuery = true, value = " SELECT * FROM heartbeat WHERE api_key = :apiKey ")
    Optional<RegistrationId> findByApiKey(@Param("apiKey") String apiKey);


}
