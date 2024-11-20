package com.example.Spring_Security_Test.controller;

import com.example.Spring_Security_Test.dao.entity.User;
import com.example.Spring_Security_Test.dto.request.LoginRequest;
import com.example.Spring_Security_Test.dto.request.SignUpRequest;
import com.example.Spring_Security_Test.dto.response.JwtResponse;
import com.example.Spring_Security_Test.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpRequest signUpRequest) {
        User user = authService.registerCustomer(signUpRequest);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCustomer(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
