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

	@PostMapping(value = "/booking")
	public ResponseEntity<BookingRecord> createBooking(@RequestBody BookingDto bookingDto);

	@PutMapping("/booking/{bookingId}")
	public ResponseEntity<BookingRecord> updateBooking(@PathVariable String bookingId,
			@RequestBody BookingDto bookingDto);

}
