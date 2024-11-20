package com.example.Spring_Security_Test.service;

import com.example.Spring_Security_Test.dao.entity.User;
import com.example.Spring_Security_Test.dao.repository.UserRepository;
import com.example.Spring_Security_Test.dto.request.LoginRequest;
import com.example.Spring_Security_Test.dto.request.SignUpRequest;
import com.example.Spring_Security_Test.dto.response.JwtResponse;
import com.example.Spring_Security_Test.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User registerCustomer(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public JwtResponse loginCustomer(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User customer = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        String accessToken = jwtService.createAccessToken(customer);
        String refreshToken = jwtService.createRefreshToken(customer);

        return new JwtResponse(accessToken, refreshToken);
    }
}
