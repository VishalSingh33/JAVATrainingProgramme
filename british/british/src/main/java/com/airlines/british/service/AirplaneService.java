package com.airlines.british.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.airlines.british.dto.FlightDto;
import com.airlines.british.dto.SeatDto;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Flight;
import com.airlines.british.entites.Seat;
import com.airlines.british.repository.AirlineInfoRepository;
import com.airlines.british.repository.AirplaneRepository;
import com.airlines.british.repository.FlightRepository;
import com.airlines.british.repository.SeatRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final FlightRepository flightRepository;
    private final AirlineInfoRepository airlineRepository;
    private final AirplaneRepository airplaneRepository;
    private final SeatRepository seatRepository;

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
    public ResponseEntity<Airplane> createAirplane(String airlineId, SeatDto seatDto) {

        AirlineInfo airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("AirlineInfo not found with id: " + airlineId));

        if (airline == null) {
            return ResponseEntity.notFound().build();
        }
        Airplane airplane = new Airplane();
        airplane.setAirplaneId(UUID.randomUUID().toString());
        airplane.setAirlineInfo(airline);

        List<Seat> seats = new ArrayList<>();
        for (String seatNumber : seatDto.getAllSeats()) {
            Seat seat = new Seat(); // Create a new Seat object in each iteration

            seat.setSeatNumber(seatNumber);
            seat.setAirplane(airplane);
            seats.add(seat);
            seat.setCreatedAt(LocalDateTime.now());
            seat.setUpdatedAt(LocalDateTime.now());
        }
        seats.sort(Comparator.comparing(Seat::getCreatedAt));
        airplane.setAllSeats(seats);

        Airplane savedAirplane = airplaneRepository.save(airplane);
        seatRepository.saveAll(seats); // not all seats are saving

        if (savedAirplane != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAirplane);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // identify what needs to be done here ?
    public ResponseEntity<Airplane> updateAirplane(String airplaneId, SeatDto seatDto) {

        // Airplane airplane = airplaneRepository.findById(airplaneId)
        //         .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + airplaneId));

        Optional<List<Airplane>> airplane = airplaneRepository.airplaneById(airplaneId);
        if (airplane == null) {
            return ResponseEntity.notFound().build();
        }
        for(Airplane airp: airplane.get()){


        }
        // List<Seat> seats = new ArrayList<>();
        // for (String seatNumber : seatDto.getAllSeats()) {
        //     Seat seat = new Seat(); // Create a new Seat object in each iteration

        //     seat.setSeatNumber(seatNumber);
        //     seat.setAirplane(airplane);
        //     seats.add(seat);
        //     seat.setUpdatedAt(LocalDateTime.now());
        // }
        // airplane.setAllSeats(seats);

        // // Save the updated airplane and seats
        // Airplane updatedAirplane = airplaneRepository.save(airplane);
        // seatRepository.saveAll(seats);
        // return ResponseEntity.ok(updatedAirplane);

        return null;

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
        List<String> seatsId = seatRepository.seatByIdList(airplaneId);
        flight.setAvailbleSeats(seatsId);
        flight.setSeatLeftToBook(seatsId.size());
        Flight savedFlight = flightRepository.save(flight);

        // how to get list of booked seats
        List<Seat> listOfseats = seatRepository.seatByAirplane(airplaneId);
        List<String> availableSeatIds = new ArrayList<>();
        List<Seat> seatsToSave = new ArrayList<>();
        for (Seat seat : listOfseats) {
            if (!seat.isFeature()) {
                availableSeatIds.add(seat.getSeatNumber());
                seat.setFlight(flight);
                seatsToSave.add(seat);
            }
        }
        seatRepository.saveAll(seatsToSave);

        if (savedFlight != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
