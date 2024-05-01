package com.airlines.british.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.airlines.british.dto.AirplaneDto;
import com.airlines.british.dto.FlightDto;
import com.airlines.british.entites.AirlineInfo;
import com.airlines.british.entites.Airplane;
import com.airlines.british.entites.Flight;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/airplane/v1")
public interface AirplaneController {

	@PostMapping(value = "/airlineInfo")
	public ResponseEntity<AirlineInfo> createAirline(@RequestParam String airlineLogo,
			@RequestParam String nameOfAirline);

	@PostMapping(value = "/airplane/{airlineId}")
	public ResponseEntity<Airplane> createAirplane(@PathVariable String airlineId,
			@RequestBody AirplaneDto airplaneDto);

	@PutMapping("/airplane/{airplaneId}")
	public ResponseEntity<Airplane> updateAirplane(@PathVariable String airplaneId,
			@RequestBody AirplaneDto airplaneDto);

	@PostMapping(value = "/flight/{airplaneId}")
	public ResponseEntity<Flight> createFlight(@PathVariable String airplaneId,
			@RequestBody FlightDto flightDto);

}
