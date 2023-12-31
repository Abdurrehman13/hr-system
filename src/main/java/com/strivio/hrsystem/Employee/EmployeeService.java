package com.strivio.hrsystem.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
    
}
