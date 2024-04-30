package com.airlines.british.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airlines.british.dto.AirplaneDto;
import com.airlines.british.dto.FlightDto;
import com.airlines.british.dto.FlightType;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Fare;
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

        // Check if the airline exists
        if (airline == null) {
            // Return 404 Not Found if airline not found
            return ResponseEntity.notFound().build();
        }
        Airplane airplane = convertToAirplaneEntity(airplaneDto);
        airplane.setAirplaneId(UUID.randomUUID().toString());
        airplane.setAvailbleSeats(airplane.getAllSeats());
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
        airplane.setAllSeats(airplaneDto.getAllSeats());
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
        airplane.setAllSeats(airplaneDto.getAllSeats());
    }

    @SuppressWarnings("unused")
    public ResponseEntity<Flight> createFlight(String airplaneId, FlightDto flightDto) {

        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + airplaneId));

        if (airplane == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        Flight flight = convertToFlightEntity(flightDto);
        String uniqueId = UUID.randomUUID().toString();
        flight.setFlightId(uniqueId);
        flight.setAirplane(airplane);
        if (flight.getOriginDateTime() != null && flight.getDestinationDateTime() != null) {
            Duration duration = Duration.between(flight.getOriginDateTime(), flight.getDestinationDateTime());
            long hours = duration.toHours(); // Get the whole hours part
            long minutes = duration.toMinutesPart(); // Get the remaining minutes part
            String durationString = String.format("%02d:%02d", hours, minutes); // Format duration as "hours:minutes"
            flight.setDuration(durationString);
        } else {
            // Handle the case where either origin or destination time is null
            flight.setDuration(null);
        }

        // if (FlightType.Business.equals(flightDto.getFlightType())) {
        // flight.setFare(FlightType.Business.getFare());
        // } else if (FlightType.Economy.equals(flightDto.getFlightType())) {
        // flight.setFare(FlightType.Economy.getFare());
        // } else if (FlightType.PremiumEconomy.equals(flightDto.getFlightType())) {
        // flight.setFare(FlightType.PremiumEconomy.getFare());
        // } else {
        // throw new ResourceNotFoundException("FlightType not Found");
        // }
        Flight savedFlight = flightRepository.save(flight);

        if (savedFlight != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Flight convertToFlightEntity(FlightDto flightDto) {

        Flight flight = new Flight();

        if (flight.getOriginDateTime() != null && flight.getDestinationDateTime() != null &&
                flight.getOriginDateTime().isBefore(flight.getDestinationDateTime())) {
            // Map fields from flightDto to Flight entity
            flight.setOrigin(flightDto.getOrigin());
            flight.setDestination(flightDto.getDestination());
            flight.setOriginDateTime(flight.getOriginDateTime());
            flight.setDestinationDateTime(flight.getDestinationDateTime());
            return flight;
        } else {
            // Throw an exception or handle the error in some way
            throw new IllegalArgumentException("Origin time must be before destination time");
        }
    }

}
