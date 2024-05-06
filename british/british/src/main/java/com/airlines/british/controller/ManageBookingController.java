package com.airlines.british.controller;

import com.airlines.british.dto.BookingDto;
import com.airlines.british.dto.SearchFlightDto;
import com.airlines.british.entites.BookingRecord;
import com.airlines.british.entites.Flight;
import com.airlines.british.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageBookingController implements BookingController {

	@Autowired
	private final BookingService bookingService;

	@Override
	public Page<BookingRecord> getBooking(int page, int size) {

		return bookingService.getBooking(page, size);
	}

	@Override
	public ResponseEntity<BookingRecord> getBookingById(String bookingId) {

		return bookingService.getBookingById(bookingId);
	}

	@Override
	public ResponseEntity<BookingRecord> createBooking(BookingDto bookingDto) {

		return bookingService.createBooking(bookingDto);
	}

	@Override
	public ResponseEntity<BookingRecord> updateBooking(String bookingId, BookingDto bookingDto) {

		return bookingService.updateBooking(bookingId, bookingDto);
	}

	@Override
	public Page<Flight> listOfFlights(SearchFlightDto searchFlightDto, int page, int size) {
		
		return bookingService.listOfFlights(searchFlightDto, page, size);
	}

}