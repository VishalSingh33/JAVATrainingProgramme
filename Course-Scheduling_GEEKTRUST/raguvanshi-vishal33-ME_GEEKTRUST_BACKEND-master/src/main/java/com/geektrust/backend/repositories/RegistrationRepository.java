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
            Registration emp = new Registration(entity.getRegId(), entity.getEmailAddress(), 
            entity.getCourseOfferingId(), entity.isAccepted());
            registrationMap.put(emp.getEmailAddress(),emp);
            return emp;
        }  
           
        registrationMap.put(entity.getEmailAddress(),entity);
        return entity;   
    }


    @Override
    public String findDetailsByCourseOfferingId(String courseOfferingId) {
        return registrationMap.values().stream()
        .filter(c->c.getCourseOfferingId().equals(courseOfferingId)).toString();
    }

    @Override
    public long count() {
        return registrationMap.size();
    }

    @Override
    public void delete(Registration entity) {
        if (registrationMap.containsValue(entity)) {
            // Find the key associated with the specified course
            String keyToRemove = null;
            for (Map.Entry<String, Registration> entry : registrationMap.entrySet()) {
                if (entry.getValue().equals(entity)) {
                    keyToRemove = entry.getKey();
                    break;
                }
            }
            // Remove the course if found
            if (keyToRemove != null) {
                registrationMap.remove(keyToRemove);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        registrationMap.remove(id);
        
    }

    @Override
    public boolean existsById(String id) {

        return registrationMap.containsKey(id);
    }

    @Override
    public List<Registration> findAll() {
        return registrationMap.values()
        .stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());
        
    }

    @Override
    public Optional<Registration> findById(String id) {

        return Optional.ofNullable(registrationMap.get(id));
    }

   

    @Override
    public String toString() {
        return "RegistrationRepository [registrationMap=" + registrationMap + "]";
    }

    @Override
    public boolean existsByEmailAndCourseOfferingId(String emailId, String courseOfferingId) {

        for (Registration registration : registrationMap.values()) {
            if (registration.getEmailAddress().equals(emailId) && registration.getCourseOfferingId().equals(courseOfferingId)) {
                return true; // Match found
            }
        }
        return false; 
        
    }

    @Override
    public boolean deleteAll() {
        registrationMap.clear();;
        return true;
    }
    
    
}