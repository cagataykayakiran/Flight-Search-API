package com.amadeus.service;

import com.amadeus.dto.AuthLoginRequest;
import com.amadeus.dto.AuthResponse;
import com.amadeus.dto.AuthRegisterRequest;
import com.amadeus.entity.User;
import com.amadeus.exception.UsernameException;
import com.amadeus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(AuthRegisterRequest authRegisterRequest) {
        if (userRepository.findByUsername(authRegisterRequest.getUsername()).isPresent()) {
            throw new UsernameException("Username already exits");
        }
        if (authRegisterRequest.getUsername().isEmpty()) {
            throw new UsernameException("Username not empty");
        }
        User user = User.builder()
                .username(authRegisterRequest.getUsername())
                .firstname("cagatay")
                .lastname("kayakiran")
                .password(passwordEncoder.encode(authRegisterRequest.getPassword()))
                .role(authRegisterRequest.getRole())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse auth(AuthLoginRequest authLoginRequest) {
        authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (authLoginRequest.getUsername(), authLoginRequest.getPassword()));
        User user = userRepository.findByUsername(authLoginRequest.getUsername()).orElseThrow(
                () -> new UsernameException("User not found"));
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
