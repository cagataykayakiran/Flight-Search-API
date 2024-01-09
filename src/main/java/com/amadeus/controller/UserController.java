package com.amadeus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    @Operation(description = "Get endpoint for user", summary = "This is a summary for user get endpoint")
    @GetMapping
    public String get() {
        return "GET:: user controller";
    }
}
