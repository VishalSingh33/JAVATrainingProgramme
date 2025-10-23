package com.example.demo.repositories;

import com.example.demo.entities.Registration;

public interface IRegistrationRepository extends CRUDRepository<Registration,String> {
    
    public String findDetailsByCourseOfferingId(String courseOfferingId);

    public boolean existsByEmailAndCourseOfferingId(String emailId, String courseOfferingId);
    
    public boolean deleteAll();
}