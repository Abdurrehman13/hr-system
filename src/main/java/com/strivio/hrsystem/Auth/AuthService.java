package com.strivio.hrsystem.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.strivio.hrsystem.Employee.EmployeeRepository;
import com.strivio.hrsystem.Security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var employee = employeeRepository.findByEmail(request.getEmail())
            .orElseThrow();
        var token = jwtService.generateToken(employee);
        return AuthResponse
            .builder().token(token).build();
    }
    
}
