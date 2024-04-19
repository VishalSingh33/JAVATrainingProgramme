package com.airlines.british.entites;

import java.time.LocalDateTime;
import java.util.List;

import com.airlines.british.dto.FlightType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "airplaneId", nullable = false, unique = true)
    private String airplaneId;

    private FlightType flightType;
	
	// private int numberofSeats; // should be in list or int is correct ?
	private List<Integer> numberofSeats;


	private List<Integer> availbleSeats; // should be present or not ?
	
	// @ManyToOne
	// @JoinTable(name = "flightsInfo", joinColumns = {
	// 		@JoinColumn(name = "flightInfoid", referencedColumnName = "flightInfoid") }, inverseJoinColumns = {
	// 				@JoinColumn(name = "airlineId", referencedColumnName = "airlineId") })
	private AirlineInfo airlineInfo;

}
