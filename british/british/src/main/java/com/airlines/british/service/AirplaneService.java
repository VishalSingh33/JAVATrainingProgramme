package com.airlines.british.service;

import java.time.Duration;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.airlines.british.dto.AirplaneDto;
import com.airlines.british.dto.FlightDto;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Flight;
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

        if (airline == null) {
            return ResponseEntity.notFound().build();
        }
        Airplane airplane = new Airplane();
        airplane.setAllSeats(airplaneDto.getAllSeats());
        airplane.setFlightType(airplaneDto.getFlightType());
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



    public ResponseEntity<Airplane> updateAirplane(String airplaneId, AirplaneDto airplaneDto) {

        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + airplaneId));

        if (airplane == null) {
            return ResponseEntity.notFound().build();
        }
        airplane.setFlightType(airplaneDto.getFlightType());
        airplane.setAllSeats(airplaneDto.getAllSeats());
        // Save the updated user entity
        Airplane updatedaAirplane = airplaneRepository.save(airplane);
        // Return ResponseEntity with the updated user
        return ResponseEntity.ok(updatedaAirplane);

    }



    @SuppressWarnings("unused")
    public ResponseEntity<Flight> createFlight(String airplaneId, FlightDto flightDto) {

        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + airplaneId));

        if (airplane == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        Flight flight = new Flight();

        if (flightDto.getOriginDateTime() != null && flightDto.getDestinationDateTime() != null &&
        flightDto.getOriginDateTime().isBefore(flightDto.getDestinationDateTime())) {
            // Map fields from flightDto to Flight entity
            flight.setOrigin(flightDto.getOrigin());
            flight.setDestination(flightDto.getDestination());
            flight.setOriginDateTime(flightDto.getOriginDateTime());
            flight.setDestinationDateTime(flightDto.getDestinationDateTime());
        } else {
            // Throw an exception or handle the error in some way
            throw new IllegalArgumentException("Origin time must be before destination time");
        }
        String uniqueId = UUID.randomUUID().toString();
        flight.setFlightId(uniqueId);
        flight.setAirplane(airplane);
        if (flightDto.getOriginDateTime() != null && flightDto.getDestinationDateTime() != null) {
            Duration duration = Duration.between(flightDto.getOriginDateTime(), flightDto.getDestinationDateTime());
            long hours = duration.toHours(); // Get the whole hours part
            long minutes = duration.toMinutesPart(); // Get the remaining minutes part
            String durationString = String.format("%02d:%02d", hours, minutes); // Format duration as "hours:minutes"
            flight.setDuration(durationString);
        } else {
            // Handle the case where either origin or destination time is null
            throw new IllegalArgumentException("Origin time must be before destination time");
        }
        int noOfSeats = flight.getAirplane().getAllSeats().size();
        flight.setSeatLeftToBook(noOfSeats);
        Flight savedFlight = flightRepository.save(flight);

        if (savedFlight != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
