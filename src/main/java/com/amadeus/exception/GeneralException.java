package com.amadeus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralException {

    @ExceptionHandler(UsernameException.class)
    public ResponseEntity<?> usernameException(UsernameException usernameException) {
        return new ResponseEntity<>(new ErrorResponse(400, usernameException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightException.class)
    public ResponseEntity<?> flightException(FlightException flightException) {
        return new ResponseEntity<>(new ErrorResponse(400, flightException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
