package com.airlines.british.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airlines.british.dto.AirplaneDto;
import com.airlines.british.dto.FlightDto;
import com.airlines.british.dto.FlightType;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Flight;
import com.airlines.british.exception.ResourceNotFoundException;
import com.airlines.british.repository.AirlineInfoRepository;
import com.airlines.british.repository.AirplaneRepository;
import com.airlines.british.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final FlightRepository flightRepository;
    private final AirlineInfoRepository airlineRepository;
    private final AirplaneRepository airplaneRepository;

    public ResponseEntity<AirlineInfo> createAirline(String airlineLogo, String nameOfAirline) {

        AirlineInfo airline = new AirlineInfo();
        String uniqueId = UUID.randomUUID().toString();
        airline.setAirlineId(uniqueId);
        airline.setAirlineLogo(airlineLogo);
        airline.setNameOfAirline(nameOfAirline);

        AirlineInfo savedAirline = airlineRepository.save(airline);
        return new ResponseEntity<>(savedAirline, HttpStatus.CREATED);
    }


    @SuppressWarnings("unused")
    public ResponseEntity<Airplane> createAirplane(String airlineId, AirplaneDto airplaneDto) {

        AirlineInfo airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + airlineId));

        // Check if the user exists
        if (airline == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        Airplane airplane = convertToAirplaneEntity(airplaneDto);
        String uniqueId = UUID.randomUUID().toString();
        airplane.setAirplaneId(uniqueId);
        airplane.setAirlineInfo(airline);
        Airplane savedAirplane = airplaneRepository.save(airplane);

        if (savedAirplane != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAirplane);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private Airplane convertToAirplaneEntity(AirplaneDto airplaneDto) {

        Airplane airplane = new Airplane();
        // Map fields from airplaneDto to Airplane entity
        airplane.setNumberofSeats(airplaneDto.getNumberofSeats());
        airplane.setFlightType(airplaneDto.getFlightType());
        return airplane;
    }
    

    public ResponseEntity<Airplane> updateAirplane(String airplaneId, AirplaneDto airplaneDto) {

        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + airplaneId));

        // Check if the user exists
        if (airplane == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        // Update the retrieved user entity with information from UserDto
        updateAirplaneFromDto(airplane, airplaneDto);
        // Save the updated user entity
        Airplane updatedaAirplane = airplaneRepository.save(airplane);
        // Return ResponseEntity with the updated user
        return ResponseEntity.ok(updatedaAirplane);

    }
    private void updateAirplaneFromDto(Airplane airplane, AirplaneDto airplaneDto) {
        // Update user properties from UserDto
        airplane.setFlightType(airplaneDto.getFlightType());
        airplane.setNumberofSeats(airplaneDto.getNumberofSeats());
    }


    @SuppressWarnings("unused")
    public ResponseEntity<Flight> createFlight(String airplaneId, FlightDto flightDto) {

        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + airplaneId));

        // Check if the user exists
        if (airplane == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        Flight flight = convertToFlightEntity(flightDto);
        String uniqueId = UUID.randomUUID().toString();
        flight.setFlightId(uniqueId);
        flight.setAirplane(airplane);
        Duration timePeriod;
        if (flight.getOriginDateTime() != null && flight.getDestinationDateTime() != null) {
            timePeriod = Duration.between(flight.getOriginDateTime(), flight.getDestinationDateTime());
        } else {
            // Handle the case where either origin or destination time is null
            timePeriod = null;
        }
        flight.setDuration(timePeriod);


        if (FlightType.Business.equals(flightDto.getFlightType())) {
            flight.setFare(FlightType.Business.getFare());
        } else if (FlightType.Economy.equals(flightDto.getFlightType())) {
            flight.setFare(FlightType.Economy.getFare());
        } else if (FlightType.PremiumEconomy.equals(flightDto.getFlightType())) {
            flight.setFare(FlightType.PremiumEconomy.getFare());
        } else {
            throw new ResourceNotFoundException("FlightType not Found");
        }
        Flight savedFlight = flightRepository.save(flight);

        if (savedFlight != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private Flight convertToFlightEntity(FlightDto flightDto) {

        Flight flight = new Flight();
        // Map fields from airplaneDto to Airplane entity
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setOriginDateTime(flightDto.getOriginDateTime());
        flight.setDestinationDateTime(flightDto.getDestinationDateTime());
        return flight;
    }

}
