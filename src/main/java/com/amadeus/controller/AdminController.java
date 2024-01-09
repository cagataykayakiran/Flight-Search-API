package com.amadeus.controller;

import com.amadeus.entity.Flight;
import com.amadeus.repository.FlightRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class AdminController {

    private final FlightRepository flightRepository;

    @Operation(description = "Get endpoint for admin", summary = "This is a summary for admin get endpoint")
    @GetMapping
    public String get() {
        return "GET:: admin controller";
    }

    @Operation(description = "Get endpoint for admin", summary = "This is a summary for admin get flights info")
    @GetMapping("/flights")
    public List<Flight> getAllFlight() {
        return flightRepository.findAll();
    }
}
