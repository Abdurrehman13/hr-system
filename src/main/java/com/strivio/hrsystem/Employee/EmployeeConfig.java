package com.strivio.hrsystem.Employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EmployeeConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository, PasswordEncoder passwordEncoder) {
        /*return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Employee waqas = new Employee(
                    "waqas", "afzal", "waqas@gmail.com", LocalDate.of(2000, 12, 22)
                );
                Employee yasir = new Employee(
                    "yasir", "abbas", "yasir@gmail.com", LocalDate.of(1998, 5, 17)
                );
                Employee dawood = new Employee(
                    "dawood", "amjad", "dawood@gmail.com", LocalDate.of(1997, 9, 4)
                );

                repository.saveAll(List.of(waqas, yasir, dawood));
            }
        };*/

        return args -> {
            Employee waqas = new Employee(
                "waqas", "afzal", 
                "waqas@gmail.com", LocalDate.of(2000, 12, 22),
                passwordEncoder.encode("123456"), Role.USER
            );
            Employee yasir = new Employee(
                "yasir", "abbas", 
                "yasir@gmail.com", LocalDate.of(1998, 5, 17),
                passwordEncoder.encode("123456"), Role.USER
            );
            Employee dawood = new Employee(
                "dawood", "amjad", 
                "dawood@gmail.com", LocalDate.of(1997, 9, 4),
                passwordEncoder.encode("123456"), Role.USER
            );
            repository.saveAll(List.of(waqas, yasir, dawood));
        };
    }
}