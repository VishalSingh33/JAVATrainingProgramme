package com.airlines.british.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirplaneDto {
    
	private List<Integer> allSeats;
	
}
