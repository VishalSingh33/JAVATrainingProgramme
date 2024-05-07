package com.airlines.british.controller;

import com.airlines.british.dto.FlightDto;
import com.airlines.british.dto.SeatDto;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Flight;
import com.airlines.british.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageAirplaneController implements AirplaneController {

	@Autowired
	private final AirplaneService airplaneService;

	@Override
	public ResponseEntity<AirlineInfo> createAirline(String airlineLogo, String nameOfAirline) {
		
		return airplaneService.createAirline(airlineLogo, nameOfAirline);
	}

	@Override
	public ResponseEntity<Airplane> createAirplane(String airlineId, SeatDto seatDto) {
		
		return airplaneService.createAirplane(airlineId, seatDto);
	}

	@Override
	public ResponseEntity<Airplane> updateAirplane(String airplaneId, SeatDto seatDto) {
		
		return airplaneService.updateAirplane(airplaneId, seatDto);
	}

	@Override
	public ResponseEntity<Flight> createFlight(String airplaneId, FlightDto flightDto) {
		
		return airplaneService.createFlight(airplaneId, flightDto);
	}

	
}