package com.airlines.british.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirplaneDto {

    private FlightType flightType;
	private List<Integer> allSeats;
	
}
