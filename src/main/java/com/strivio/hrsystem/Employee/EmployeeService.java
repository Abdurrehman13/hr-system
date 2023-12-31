package com.strivio.hrsystem.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    //email must be unique
    public void saveEmployee(Employee employee) {
        Optional<Employee> employeeOptional = 
            repository.findByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        repository.save(employee);
    }

    @Transactional
    public void updateEmployee(Employee emp) {
        Employee dbEmployee = repository.findById(emp.getId())
            .orElseThrow(() -> new IllegalStateException(
                "Employee with id" + emp.getId() + " not found"
            ));
        
        
        if (!emp.getEmail().equals(dbEmployee.getEmail())) {
            Optional<Employee> employeeOptional = repository.findByEmail(emp.getEmail());
            if (employeeOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            dbEmployee.setEmail(emp.getEmail());
        }
        
        dbEmployee.setFirstname(emp.getFirstname());
        dbEmployee.setLastname(emp.getLastname());
        dbEmployee.setDob(emp.getDob());
   
    }

    public void deleteEmployee(Long employeeId) {
        repository.deleteById(employeeId);
    }
    
}
