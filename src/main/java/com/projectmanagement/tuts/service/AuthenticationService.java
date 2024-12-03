package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.AuthenticationRequest;
import com.projectmanagement.tuts.DTO.AuthenticationResponse;
import com.projectmanagement.tuts.DTO.RegisterRequest;
import com.projectmanagement.tuts.Entity.Role;
import com.projectmanagement.tuts.config.JwtService;
import com.projectmanagement.tuts.exception.AuthenticationException;
import com.projectmanagement.tuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthenticationResponse.builder()
                    .message("User with email already exist")
                    .build();
        }

        var user = com.projectmanagement.tuts.Entity.User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("User Successfully created")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Retrieve the user from the repository
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Generate the JWT token
            var jwtToken = jwtService.generateToken(user);

            // Return a successful response
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .message("User successfully logged in")
                    .build();
        } catch (BadCredentialsException e) {
            // Handle incorrect email or password
            throw new AuthenticationException("Invalid email or password");
        }
    }
}