package com.airlines.british.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDto {

	private String origin;

	private String destination;

	private LocalDateTime originDateTime;

	private LocalDateTime destinationDateTime;

	private int seatLeftToBook;

	private FlightType flightType;
    
}
