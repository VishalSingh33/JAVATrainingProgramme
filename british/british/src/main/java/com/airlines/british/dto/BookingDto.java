package com.airlines.british.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDto {

	private String userId; 

	private String flightId;

	private int seatNumber;

	private String flightType;

}
