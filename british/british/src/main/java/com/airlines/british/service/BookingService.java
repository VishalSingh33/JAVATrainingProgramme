package com.airlines.british.service;

import java.time.LocalDateTime;
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
import com.airlines.british.entites.BookingRecord;
import com.airlines.british.entites.Fare;
import com.airlines.british.entites.Flight;
import com.airlines.british.entites.Passenger;
import com.airlines.british.entites.User;
import com.airlines.british.exception.BookingControllerException;
import com.airlines.british.exception.ResourceNotFoundException;
import com.airlines.british.repository.BookingRecordRepository;
import com.airlines.british.repository.FareRepository;
import com.airlines.british.repository.FlightRepository;
import com.airlines.british.repository.PassengerRepository;
import com.airlines.british.repository.UserRepository;
import com.airlines.british.service.BookingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final BookingRecordRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final FareRepository fareRepository;
    private final PassengerRepository passengerRepository;

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

    // @SuppressWarnings("unused")
    public ResponseEntity<BookingRecord> createBooking(String userId, String flightId, BookingDto bookingDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + flightId));

        if (existingUser == null || flight == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        BookingRecord booking = new BookingRecord();

        List<Integer> availableSeats = flight.getAirplane().getAvailbleSeats();

        if (availableSeats.contains(bookingDto.getSeatNumber())) {
            booking.setSeatNumber(bookingDto.getSeatNumber());
        } else {
            // If specific seat number is not provided , randomly assigna seat
            Random random = new Random();
            int randomSeatIndex = random.nextInt(availableSeats.size());
            int randomSeatNumber = availableSeats.get(randomSeatIndex);
            booking.setSeatNumber(randomSeatNumber);
        }
        int noOfSeats = flight.getAirplane().getAllSeats().size();

        if (booking.getBookingStatus().equals(BookingStatus.BOOKED) && noOfSeats > 0) {
            booking.setBookingStatus(BookingStatus.BOOKED);
            availableSeats.remove(booking.getSeatNumber());
            noOfSeats = flight.getSeatLeftToBook() - booking.getPassengers().size();
        }
        if (noOfSeats < 0) {
            throw new BookingControllerException("No Seat Left to Book");
        }
        flight.setSeatLeftToBook(noOfSeats);
        booking.setPassengers(bookingDto.getPassengers()); // is it correct ??

        Passenger passenger = new Passenger();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        booking.setFlight(flight);
        passenger.setPassengerId(UUID.randomUUID().toString());
        passenger.setUser(existingUser);
        passenger.setBookingId(booking.getBookingId());

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
        fareRepository.save(fare);
        bookingRepository.save(booking);
        passengerRepository.save(passenger);
        flightRepository.save(flight);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);

    }

    public ResponseEntity<BookingRecord> updateBooking(String userId, String bookingId, BookingDto bookingDto) {

        BookingRecord booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingId));

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (existingUser == null || booking == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }

        Flight flight = new Flight();
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
        Passenger passenger = null;
        for (int i = 0; i < booking.getPassengers().size(); i++) {
            if (existingUser.getUserId() == booking.getPassengers().get(i).getUser().getUserId()) {
                passenger = passengerRepository.findById(booking.getPassengers().get(i).getPassengerId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            }
        }
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        String fareId = passenger.getFareId();
        Fare fare = fareRepository.findById(passenger.getPassengerId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + fareId));

        fare.setFare(booking.getBookingFare());

        booking.setBookingStatus(bookingDto.getBookingStatus());
        List<Integer> availableSeats = flight.getAirplane().getAvailbleSeats();
        int noOfSeats = flight.getAirplane().getAllSeats().size();
        if (noOfSeats <= 0) {
            throw new BookingControllerException("No Seat Left to Book");
        }
        if (bookingDto.getBookingStatus().equals(BookingStatus.CANCELLED)) {
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
                int randomSeatNumber = availableSeats.get(randomSeatIndex);
                availableSeats.add(booking.getSeatNumber());
                booking.setSeatNumber(randomSeatNumber);
                availableSeats.remove(booking.getSeatNumber());
            }
            booking.setBookingStatus(BookingStatus.BOOKED);
        }
        if (noOfSeats < 0) {
            throw new BookingControllerException("No Seat Left to Book");
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

}
