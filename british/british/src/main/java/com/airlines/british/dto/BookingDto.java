package com.airlines.british.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDto {

	private String userId; 

	private String flightId;

	private String seatNumber;

	private String flightType;

	// TO get FlightId follow below
	//SEARCH{ 
	//from
	//to
	// departure dateTime

	//extra:
	// return 
	// more than one user booking at a time
    // }

	// AFTER SEARCH

	// List<Flights> 
	//after selecting flight

	// User Details
	// a new entites: Seats -> 
	

}
