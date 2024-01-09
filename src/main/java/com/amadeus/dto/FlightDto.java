package com.amadeus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private String departureCity;
    private String arrivalCity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDateTime;
    private double price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date getReturnDateTime() {
        return returnDateTime;
    }
}

