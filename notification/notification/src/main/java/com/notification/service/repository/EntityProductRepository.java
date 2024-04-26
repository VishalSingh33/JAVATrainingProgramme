// package com.notification.service.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import com.zyapaar.chatservice.entities.EntityProduct;

// @Repository
// public interface EntityProductRepository extends JpaRepository<EntityProduct,String> {

//   // @Query(nativeQuery = true,
//   //   value = "select e.user_id  from entity_products ep " + 
//   //   "inner join entities e on ep.entities_id = e.id " + 
//   //   "where ep.id = :productId " )
//   // String getSuperAdmin(String productId);

// }