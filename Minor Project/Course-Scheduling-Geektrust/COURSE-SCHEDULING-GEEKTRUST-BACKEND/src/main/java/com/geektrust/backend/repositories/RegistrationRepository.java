package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Registration;

public class RegistrationRepository implements IRegistrationRepository {
    private final Map<String,Registration> registrationMap;
    
    public RegistrationRepository(){
        registrationMap = new HashMap<String,Registration>();
    }

    public RegistrationRepository(Map<String, Registration> registrationMap) {
        this.registrationMap = registrationMap;
       
    }

    @Override
    public Registration save(Registration entity) {
        if(entity.getCourseOfferingId()!=null)  {
            Registration emp = new Registration(entity.getRegId(), entity.getEmailAddress(), entity.getCourseOfferingId(), entity.isAccepted());
            registrationMap.put(emp.getEmailAddress(),emp);
            return emp;
        }  
           
        registrationMap.put(entity.getEmailAddress(),entity);
        return entity;   
    }


    @Override
    public String findDetailsByCourseOfferingId(String courseOfferingId) {
        return registrationMap.values().stream()
        .filter(c->c.getCourseOfferingId().equals(courseOfferingId))
        .toString();
    }

    @Override
    public long count() {
        return registrationMap.size();
    }

    @Override
    public void delete(Registration entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        registrationMap.remove(id);
        
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Registration> findAll() {
        return registrationMap.values()
        .stream()
        .sorted((o1, o2) -> o1.compareTo(o2))
        .collect(Collectors.toList());
        
    }

    @Override
    public Optional<Registration> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

   

    @Override
    public String toString() {
        return "RegistrationRepository [registrationMap=" + registrationMap + "]";
    }
    
    
}
