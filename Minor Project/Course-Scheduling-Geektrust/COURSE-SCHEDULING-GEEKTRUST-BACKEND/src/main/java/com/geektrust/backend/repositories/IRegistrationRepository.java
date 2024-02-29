package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.Registration;

public interface IRegistrationRepository extends CRUDRepository<Registration,String> {
    public String findDetailsByCourseOfferingId(String courseOfferingId); 
}
