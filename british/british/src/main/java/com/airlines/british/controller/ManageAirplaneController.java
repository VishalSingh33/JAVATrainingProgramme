package com.airlines.british.controller;

import com.airlines.british.dto.AirplaneDto;
import com.airlines.british.dto.FlightDto;
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
	public ResponseEntity<Airplane> createAirplane(String airlineId, AirplaneDto airplaneDto) {
		
		return airplaneService.createAirplane(airlineId, airplaneDto);
	}

	@Override
	public ResponseEntity<Airplane> updateAirplane(String airplaneId, AirplaneDto airplaneDto) {
		
		return airplaneService.updateAirplane(airplaneId, airplaneDto);
	}

	@Override
	public ResponseEntity<Flight> createFlight(String airplaneId, FlightDto flightDto) {
		
		return airplaneService.createFlight(airplaneId, flightDto);
	}

	
}