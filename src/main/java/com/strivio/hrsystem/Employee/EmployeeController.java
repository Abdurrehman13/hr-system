package com.strivio.hrsystem.Employee;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strivio.hrsystem.Auth.AuthResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<AuthResponse> saveEmployee(@Validated @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    } 

    @PutMapping
    public void updateEmployee(@Validated @RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}
