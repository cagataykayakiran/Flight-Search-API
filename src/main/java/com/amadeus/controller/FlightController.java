package com.amadeus.controller;

import com.amadeus.entity.Flight;
import com.amadeus.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RequestMapping("/api/v1")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<List<?>> getFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDateTime) {

        return ResponseEntity.ok(flightService.searchFlights(departureCity, arrivalCity, departureDateTime, returnDateTime));
    }

    @PostMapping("/addFlight")
    public ResponseEntity<Flight> addFlight(@RequestParam String departureCity,
                                            @RequestParam String arrivalCity,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDateTime,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDateTime,
                                            @RequestParam double price) {

        return ResponseEntity.ok(flightService.addFlight(departureCity, arrivalCity, departureDateTime, returnDateTime,price));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        flightService.deleteById(id);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @PutMapping("/updateFlight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id,
                                               @RequestParam String departureCity,
                                               @RequestParam String arrivalCity,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDateTime,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDateTime,
                                               @RequestParam Double price) {
        Flight updatedFlight = flightService.updateFlight(id, departureCity, arrivalCity, departureDateTime, returnDateTime, price);
        return ResponseEntity.ok(updatedFlight);
    }

}
