package com.amadeus.controller;

import com.amadeus.dto.AuthLoginRequest;
import com.amadeus.dto.AuthResponse;
import com.amadeus.dto.AuthRegisterRequest;
import com.amadeus.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegisterRequest authRegisterRequest) {
        return ResponseEntity.ok(authenticationService.register(authRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthLoginRequest authLoginRequest) {
        return ResponseEntity.ok(authenticationService.auth(authLoginRequest));
    }
}
