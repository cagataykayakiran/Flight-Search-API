package com.amadeus.controller;

import com.amadeus.service.DataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/import")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class ImportController {

    private final DataService dataService;

    @GetMapping("/fromMockApi")
    public ResponseEntity<String> importFromMockApi() {
        return ResponseEntity.ok(dataService.importFromMockApi());
    }
}
