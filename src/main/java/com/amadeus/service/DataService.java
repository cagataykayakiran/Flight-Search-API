package com.amadeus.service;

import com.amadeus.entity.Flight;
import com.amadeus.repository.FlightRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;
    @Value("${API_URL}")
    private String API_URL;

    public String importFromMockApi() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);
        String jsonData = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Flight> flights = objectMapper.readValue(jsonData, new TypeReference<List<Flight>>() {});
            flightRepository.saveAll(flights);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "Success data import from API";
    }
}
