package com.amadeus.service;

import com.amadeus.dto.FlightDto;
import com.amadeus.entity.Airport;
import com.amadeus.entity.Flight;
import com.amadeus.exception.FlightException;
import com.amadeus.repository.AirportRepository;
import com.amadeus.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<FlightDto> searchFlights(String departureCity, String arrivalCity, Date departureDateTime, Date returnDateTime) {
        List<Flight> flights;
        if (returnDateTime != null) {
            flights = flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAndReturnDateTime(
                    departureCity, arrivalCity, departureDateTime, returnDateTime);
        } else {
            flights = flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTime(
                    departureCity, arrivalCity, departureDateTime);
        }
        List<FlightDto> flightInfoList = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightInfo = getFlightInfo(returnDateTime, flight);
            flightInfoList.add(flightInfo);
        }
        return flightInfoList;
    }

    private static FlightDto getFlightInfo(Date returnDateTime, Flight flight) {
        FlightDto flightInfo;
        if (returnDateTime != null) {
            flightInfo = new FlightDto();
            flightInfo.setReturnDateTime(flight.getReturnDateTime());
        } else {
            flightInfo = new FlightDto();
        }
        flightInfo.setDepartureCity(flight.getDepartureAirport().getCity());
        flightInfo.setArrivalCity(flight.getArrivalAirport().getCity());
        flightInfo.setDepartureDateTime(flight.getDepartureDateTime());
        flightInfo.setPrice(flight.getPrice());
        return flightInfo;
    }

    public Flight addFlight(String departureCity, String arrivalCity, Date departureDateTime, Date returnDateTime, double price) {
        Airport departureAirport = airportRepository.findByCity(departureCity);
        if (departureAirport == null) {
            departureAirport = new Airport(departureCity);
            airportRepository.save(departureAirport);
        }
        Airport arrivalAirport = airportRepository.findByCity(arrivalCity);
        if (arrivalAirport == null) {
            arrivalAirport = new Airport(arrivalCity);
            airportRepository.save(arrivalAirport);
        }
        Flight flight = Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(departureDateTime)
                .returnDateTime(returnDateTime)
                .price(price)
                .build();
        return flightRepository.save(flight);
    }

    public void deleteById(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            flightRepository.deleteById(id);
        }
        else {
            throw new FlightException("Flight not found");
        }
    }

    public Flight updateFlight(Long id, String departureCity, String arrivalCity, Date departureDateTime, Date returnDateTime, Double price) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            flight.setDepartureAirport(new Airport(departureCity));
            flight.setArrivalAirport(new Airport(arrivalCity));
            flight.setDepartureDateTime(departureDateTime);
            flight.setReturnDateTime(returnDateTime);
            flight.setPrice(price);
            return flightRepository.save(flight);
        } else {
            throw new FlightException("Flight not found");
        }
    }
}
