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
import com.airlines.british.entites.BookingRecord;
import com.airlines.british.entites.Flight;
import com.airlines.british.entites.Passenger;
import com.airlines.british.entites.User;
import com.airlines.british.exception.BookingControllerException;
import com.airlines.british.repository.BookingRecordRepository;
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

    @SuppressWarnings("unused")
    public ResponseEntity<BookingRecord> createBooking(String userId, String flightId, BookingDto bookingDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + flightId));

        // Check if the user exists
        if (existingUser == null || flight == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        BookingRecord booking = convertToBookingEntity(bookingDto);
        Passenger passenger = new Passenger();
        String uniqueId = UUID.randomUUID().toString();
        booking.setBookingId(uniqueId);
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        booking.setFlight(flight);
        passenger.setPassengerId(uniqueId);
        passenger.setUser(existingUser);
        passenger.setBookingId(booking.getBookingId());
        // log.info("booking.getEntityLogo() entitiesList : {}", entitiesList);
        // Save the Booking entity
        BookingRecord savedUser = bookingRepository.save(booking);
        Passenger savedPassenger = passengerRepository.save(passenger);
        flightRepository.save(flight);

        if (savedUser != null || savedPassenger != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private BookingRecord convertToBookingEntity(BookingDto bookingDto) {

        BookingRecord booking = new BookingRecord();
        Flight flight = new Flight();

        List<Integer> availableSeats = flight.getAirplane().getAvailbleSeats();
        // bookingDto.getSeatNumber() != null &&
            if (availableSeats.contains(bookingDto.getSeatNumber())) {
                booking.setSeatNumber(bookingDto.getSeatNumber());
                //continue; let it get checked by whole remaning seat
            } else {
                // If specific seat number is not provided , randomly assigna seat
                Random random = new Random();
                int randomSeatIndex = random.nextInt(availableSeats.size());
                int randomSeatNumber = availableSeats.get(randomSeatIndex);
                booking.setSeatNumber(randomSeatNumber);
            }
        booking.setBookingStatus(BookingStatus.BOOKED);
        int noOfSeats = flight.getAirplane().getNumberofSeats().size();

        if (booking.getBookingStatus().equals(BookingStatus.BOOKED) && noOfSeats > 0) {
            booking.setBookingStatus(BookingStatus.BOOKED);
            noOfSeats = flight.getSeatLeftToBook() - booking.getPassengers().size();
        }
        if (noOfSeats <= 0) {
            throw new BookingControllerException("No Seat Left to Book");
        }
        flight.setSeatLeftToBook(noOfSeats);

        booking.setPassengers(bookingDto.getPassengers()); // is it correct ??
        return booking;
    }

    public ResponseEntity<BookingRecord> updateBooking(String userId, String bookingId, BookingDto bookingDto) {

        BookingRecord booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingId));

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user exists
        if (existingUser == null || booking == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }

        // Update the retrieved BookingRecord entity with information from UserDto
        updateBookingFromDto(booking, bookingDto);
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        // Save the updated BookingRecord entity
        BookingRecord updatedBooking = bookingRepository.save(booking);
        // Return ResponseEntity with the updated BookingRecord
        return ResponseEntity.ok(updatedBooking);

    }
    private void updateBookingFromDto(BookingRecord booking, BookingDto bookingDto) {
        // Update BookingRecord properties from UserDto
        // check updated seat is vacant or not
        // if seat is ACTIVE or booked then let the seatNumber remain same, if cancelled
        // the reduce the count
        Flight flight = new Flight();
        booking.setBookingStatus(bookingDto.getBookingStatus());
        int noOfSeats = flight.getAirplane().getNumberofSeats().size();
        if (noOfSeats <= 0) {
            throw new BookingControllerException("No Seat Left to Book");
        }
        if(bookingDto.getBookingStatus().equals(BookingStatus.CANCELLED)){
            booking.setBookingStatus(BookingStatus.CANCELLED);
            noOfSeats = flight.getSeatLeftToBook() + 1; 
            //check if a person cancels more tha one ticket than handle that case
        } else {
        List<Integer> availableSeats = flight.getAirplane().getAvailbleSeats();
        // bookingDto.getSeatNumber() != null &&
            if (availableSeats.contains(bookingDto.getSeatNumber())) {
                booking.setSeatNumber(bookingDto.getSeatNumber());
                //continue; let it get checked by whole remaning seat
            } else {
                // If specific seat number is not provided , randomly assigna seat
                Random random = new Random();
                int randomSeatIndex = random.nextInt(availableSeats.size());
                int randomSeatNumber = availableSeats.get(randomSeatIndex);
                booking.setSeatNumber(randomSeatNumber);
            }
        booking.setBookingStatus(BookingStatus.BOOKED);
        }
        if (noOfSeats <= 0) {
            throw new BookingControllerException("No Seat Left to Book");
        }
        flight.setSeatLeftToBook(noOfSeats);
        flightRepository.save(flight);
    }

    public void deleteUser(String bookingId) {

        bookingRepository.deleteById(bookingId);
    }

}
