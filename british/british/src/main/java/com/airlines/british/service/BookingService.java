package com.airlines.british.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
// import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.MultiMap;
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
import org.springframework.util.MultiValueMap;
// import reactor.core.publisher.Mono;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@Service
public class BookingService {

    // private final WebClient webClient;
    // private final RestClient restClient;
    // private final RestTemplate restTemplate;
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

    public ResponseEntity<BookingRecord> createBooking(BookingDto bookingDto) {

        User existingUser = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDto.getUserId()));

        Flight flight = flightRepository.findById(bookingDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + bookingDto.getFlightId()));

        String userId = bookingDto.getUserId();
        LocalDate today = LocalDate.now();
        // Count the number of bookings made by the user on the current day
        long count = passengerRepository.countByUserId(userId, today);
        // Check if the user has already made 3 bookings on the current day
        int bookingLimitPerDay = 3;
        if (count >= bookingLimitPerDay) {
            // Return a response indicating that the user has exceeded the booking limit
            throw new ResourceNotFoundException("User has already booked the maximum number of tickets for today.");
            // return ResponseEntity.badRequest().body("User has already booked the maximum
            // number of tickets for today.");
        }

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
        passenger.setCreatedAt(LocalDateTime.now());
        passenger.setUpdatedAt(LocalDateTime.now());
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
        seat.toBuilder().isFeature(true).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("totalAmount", "10.0");

        // Set form data
        MultiValueMap<String, Double> body = new LinkedMultiValueMap<>();
        body.add("totalAmount", 10.00);

        // Combine headers and form data into an HttpEntity
        HttpEntity<MultiValueMap<String, Double>> requestEntity = new HttpEntity<>(body, headers);

        // Make the POST request
        // ResponseEntity<BookingDto> responseEntity = restTemplate
        //         .exchange("http://localhost:8080/orders/payment", HttpMethod.POST, requestEntity, BookingDto.class);

        // // Check for a successful response
        // if (responseEntity.getStatusCode() == HttpStatus.OK) {
        //     System.out.println(responseEntity);
        // } else {
        //     // Handle error response
        //     throw new RuntimeException("Failed to make payment: " + responseEntity.getStatusCode());
        // }

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
        passenger.setUpdatedAt(LocalDateTime.now());
        flight.setSeatLeftToBook(noOfSeats);
        flightRepository.save(flight);
        fareRepository.save(fare);
        passengerRepository.save(passenger);
        booking.setUpdateBookingDateTime(LocalDateTime.now());
        // Save the updated BookingRecord entity
        BookingRecord updatedBooking = bookingRepository.save(booking);
        // Return ResponseEntity with the updated BookingRecord
        return ResponseEntity.ok(updatedBooking);

    }

    public Page<Flight> listOfFlights(SearchFlightDto searchFlightDto, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Flight> flights = flightRepository.findByOriginDateTimeBetween(searchFlightDto.getDateTime(),
                searchFlightDto.getOrigin(), searchFlightDto.getDestination(), searchFlightDto.getStartTime(),
                searchFlightDto.getEndTime(), pageRequest);
        return flights;
    }

}
