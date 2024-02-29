package com.geektrust.backend.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Employee;


public class EmployeeRepository implements IEmployeeRepository {
  
    private final Map<String,Employee> employeeMap;
   

    public EmployeeRepository(){
        employeeMap = new HashMap<String,Employee>();
    }

    public EmployeeRepository(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
       
    }

    @Override
    public Employee save(Employee entity) {  
        if(entity.getEmailAddress()!=null)  {
            Employee emp = new Employee(entity.getEmailAddress());
            employeeMap.put(emp.getEmailAddress(),emp);
            return emp;
        }  
           
        employeeMap.put(entity.getEmailAddress(),entity);
        return entity;    
           
    }

    @Override
    public List<Employee> findAll() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Employee findNameByEmail(String emailAddress) {
    return employeeMap.values().stream().filter(s->s.getEmailAddress().equals(emailAddress)).findAny().get();
  
    }

    
    @Override
    public long count() {
        return employeeMap.size();
    }

    @Override
    public void delete(Employee entity) {
        String email=entity.getEmailAddress();
        employeeMap.remove(email);
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return null;
    }


}
