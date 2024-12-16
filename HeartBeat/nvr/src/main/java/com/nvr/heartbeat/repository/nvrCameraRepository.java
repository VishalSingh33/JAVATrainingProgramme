// package com.nvr.heartbeat.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import com.nvr.heartbeat.entites.NvrCamera;

// @Repository
// public interface nvrCameraRepository extends JpaRepository<NvrCamera, Long> {

//     @Query(nativeQuery = true, value = "SELECT * FROM nvr_info WHERE nvr_id = :nvrId")
//     Optional<NvrCamera> findByNvrId(@Param("nvrId") String nvrId);

//     @Query(nativeQuery = true, value = "Select * from cctv_info where nvr_id = :nvrId")
//     List<Camera> findAllByNvrId(@Param("nvrId") String nvrId);

// }
