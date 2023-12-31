package com.strivio.hrsystem.Employee;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(
        name = "employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "id", updatable = false)
    private Long Id;

    @NotNull @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstname;
    
    @NotNull @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @NotNull @NotBlank
    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @NotNull
    @Column(nullable = false, columnDefinition = "")
    private LocalDate dob;

    @Transient
    private Integer age;

    public Employee(String firstname, String lastname, String email, LocalDate dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
    
}
