package com.airlines.british.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.airlines.british.dto.BookingDto;
import com.airlines.british.entites.BookingRecord;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/booking/v1")
public interface BookingController {

	@GetMapping(value = "/booking")
	public Page<BookingRecord> getBooking(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size);

	@GetMapping(value = "/booking/{bookingId}")
	public ResponseEntity<BookingRecord> getBookingById(@PathVariable String bookingId);

	@PostMapping(value = "/booking/{userId}/{flightId}")
	public ResponseEntity<BookingRecord> createBooking(@PathVariable String userId,
			@PathVariable String flightId, @RequestBody BookingDto bookingDto);

	@PutMapping("/booking/{userId}/{bookingId}")
	public ResponseEntity<BookingRecord> updateBooking(@RequestBody String userId,
			@PathVariable String bookingId, @RequestBody BookingDto bookingDto);

	@DeleteMapping("/booking/{bookingId}")
	public void deleteUser(@PathVariable String bookingId);

}
