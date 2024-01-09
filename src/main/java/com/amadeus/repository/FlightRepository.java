package com.amadeus.repository;

import com.amadeus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAndReturnDateTime(
            String departureCity, String arrivalCity, Date departureDateTime, Date returnDateTime);
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTime(
            String departureCity, String arrivalCity, Date departureDateTime);
}
