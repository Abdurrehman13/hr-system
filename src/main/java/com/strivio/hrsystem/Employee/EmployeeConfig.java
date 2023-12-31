package com.strivio.hrsystem.Employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
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
                "waqas@gmail.com", LocalDate.of(2000, 12, 22)
            );
            Employee yasir = new Employee(
                "yasir", "abbas", 
                "yasir@gmail.com", LocalDate.of(1998, 5, 17)
            );
            Employee dawood = new Employee(
                "dawood", "amjad", 
                "dawood@gmail.com", LocalDate.of(1997, 9, 4)
            );
            repository.saveAll(List.of(waqas, yasir, dawood));
        };
    }
}