package com.airlines.british.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.airlines.british.dto.BookingDto;
import com.airlines.british.dto.BookingStatus;
import com.airlines.british.dto.FlightType;
import com.airlines.british.dto.SearchFlightDto;
import com.airlines.british.entites.BookingRecord;
import com.airlines.british.entites.Fare;
import com.airlines.british.entites.Flight;
import com.airlines.british.entites.Passenger;
import com.airlines.british.entites.Seat;
import com.airlines.british.entites.User;
import com.airlines.british.exception.BookingException;
import com.airlines.british.exception.ResourceNotFoundException;
import com.airlines.british.repository.AirplaneRepository;
import com.airlines.british.repository.BookingRecordRepository;
import com.airlines.british.repository.FareRepository;
import com.airlines.british.repository.FlightRepository;
import com.airlines.british.repository.PassengerRepository;
import com.airlines.british.repository.SeatRepository;
import com.airlines.british.repository.UserRepository;
import com.airlines.british.service.BookingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final UserRepository userRepository;
    private final FareRepository fareRepository;
    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final PassengerRepository passengerRepository;
    private final BookingRecordRepository bookingRepository;

    public Page<BookingRecord> getBooking(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return bookingRepository.findAll(pageable);
    }

    public ResponseEntity<BookingRecord> getBookingById(String bookingId) {

        BookingRecord booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingId));

        if (booking != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //not sure : if same user tries to book a seat in same flight he should be allowed or not
    // -> doing above thing is cretaing a new passengerId again in bookingRecord table
    public ResponseEntity<BookingRecord> createBooking(BookingDto bookingDto) {

        User existingUser = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDto.getUserId()));

        Flight flight = flightRepository.findById(bookingDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + bookingDto.getFlightId()));

        if (existingUser == null || flight == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        BookingRecord booking = new BookingRecord();

        List<String> availableSeats = flight.getAvailbleSeats();
       
        if (availableSeats.contains(bookingDto.getSeatNumber())) {
            booking.setSeatNumber(bookingDto.getSeatNumber());
            booking.setBookingStatus(BookingStatus.BOOKED);
            
        } else {
            // If specific seat number is not provided , randomly assigna seat
            Random random = new Random();
            int randomSeatIndex = random.nextInt(availableSeats.size());
            String randomSeatNumber = availableSeats.get(randomSeatIndex);
            booking.setSeatNumber(randomSeatNumber);
            booking.setBookingStatus(BookingStatus.BOOKED);
        }
        availableSeats.remove(booking.getSeatNumber());
        flight.setSeatLeftToBook(flight.getAvailbleSeats().size());

        Passenger passenger = new Passenger();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        booking.setFlight(flight);
        passenger.setPassengerId(UUID.randomUUID().toString());
        passenger.setUser(existingUser);
        passenger.setBookingId(booking.getBookingId());
        booking.setPassengerId(passenger.getPassengerId());

        String flightType = bookingDto.getFlightType();
        if ("Business".equals(flightType)) {
            booking.setBookingFare(FlightType.Business.getFare());
        } else if ("Economy".equals(flightType)) {
            booking.setBookingFare(FlightType.Economy.getFare());
        } else if ("PremiumEconomy".equals(flightType)) {
            booking.setBookingFare(FlightType.PremiumEconomy.getFare());
        } else {
            throw new ResourceNotFoundException("FlightType not Found");
        }

        Fare fare = new Fare();
        fare.setFareId(UUID.randomUUID().toString());
        fare.setFareDateTime(LocalDateTime.now());
        fare.setFlightType(bookingDto.getFlightType()); // recheck
        fare.setFare(booking.getBookingFare());
        fare.setBookingId(booking.getBookingId());
        passenger.setFareId(fare.getFareId());
        // log.info("booking.getEntityLogo() entitiesList : {}", entitiesList);
        // Save the entity
        Seat seat = seatRepository.findById(booking.getSeatNumber())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + booking.getSeatNumber()));
        seat.toBuilder().isBooked(true).build();
        seatRepository.save(seat);
        fareRepository.save(fare);
        bookingRepository.save(booking);
        passengerRepository.save(passenger);
        flightRepository.save(flight);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);

    }

    
    public ResponseEntity<BookingRecord> updateBooking(String bookingId, BookingDto bookingDto) {

        BookingRecord booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingId));

        User existingUser = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDto.getUserId()));

        Passenger passenger = passengerRepository.findById(booking.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + booking.getPassengerId()));

        Flight flight = flightRepository.findById(bookingDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + bookingDto.getFlightId()));

        if (existingUser == null || booking == null || passenger == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }

        String flightType = bookingDto.getFlightType();
        if ("Business".equals(flightType)) {
            booking.setBookingFare(FlightType.Business.getFare());
        } else if ("Economy".equals(flightType)) {
            booking.setBookingFare(FlightType.Economy.getFare());
        } else if ("PremiumEconomy".equals(flightType)) {
            booking.setBookingFare(FlightType.PremiumEconomy.getFare());
        } else {
            throw new ResourceNotFoundException("FlightType not Found");
        }

        if (!existingUser.getUserId().equals(passenger.getUser().getUserId())) {
            throw new IllegalArgumentException("User ID provided does not match with Passenger");
        }
        String fareId = passenger.getFareId();
        Fare fare = fareRepository.findById(fareId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + fareId));

        fare.setFare(booking.getBookingFare());

        booking.setBookingStatus(booking.getBookingStatus());
        List<String> availableSeats = flight.getAvailbleSeats();
        int noOfSeats = flight.getAirplane().getAllSeats().size();
        if (noOfSeats <= 0) {
            throw new BookingException("No Seat Left to Book");
        }
        if (booking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
            availableSeats.add(booking.getSeatNumber());
            noOfSeats = flight.getSeatLeftToBook() + 1;

            // check if a person cancels more tha one ticket than handle that case ??
        } else {
            if (availableSeats.contains(bookingDto.getSeatNumber())) {
                availableSeats.add(booking.getSeatNumber());
                booking.setSeatNumber(bookingDto.getSeatNumber());
                availableSeats.remove(bookingDto.getSeatNumber());
            } else {
                // If specific seat number is not provided , randomly assigna seat
                Random random = new Random();
                int randomSeatIndex = random.nextInt(availableSeats.size());
                String randomSeatNumber = availableSeats.get(randomSeatIndex);
                availableSeats.add(booking.getSeatNumber());
                booking.setSeatNumber(randomSeatNumber);
                availableSeats.remove(booking.getSeatNumber());
            }
            booking.setBookingStatus(BookingStatus.BOOKED);
        }
        if (noOfSeats < 0) {
            throw new BookingException("No Seat Left to Book");
        }
        flight.setSeatLeftToBook(noOfSeats);
        flightRepository.save(flight);
        fareRepository.save(fare);
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        // Save the updated BookingRecord entity
        BookingRecord updatedBooking = bookingRepository.save(booking);
        // Return ResponseEntity with the updated BookingRecord
        return ResponseEntity.ok(updatedBooking);

    }

    public Page<Flight> listOfFlights(SearchFlightDto searchFlightDto, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Flight> flights =  flightRepository.findByOriginDateTimeBetween(searchFlightDto.getDateTime(), searchFlightDto.getOrigin(), searchFlightDto.getDestination(), searchFlightDto.getStartTime(), searchFlightDto.getEndTime(), pageRequest);
        return flights;
    }

}
