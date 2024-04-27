package com.airlines.british.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.airlines.british.entites.Flight;
import com.airlines.british.entites.Passenger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDto {

	private Flight flight;
	// private String flightId;
	// private String origin;
	// private String destination;
	// private double fare;
	// private LocalDateTime originDateTime;
	// private LocalDateTime destinationDateTime;

	private BookingStatus bookingStatus;

	private int seatNumber;

	private String flightType;
	
	private List<Passenger> passengers;

}
