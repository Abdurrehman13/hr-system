package com.strivio.hrsystem.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee implements UserDetails {

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

    @NotNull @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(String firstname, String lastname, String email, 
                    LocalDate dob, String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.role = role;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
