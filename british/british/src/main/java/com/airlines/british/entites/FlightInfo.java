package com.airlines.british.entites;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "flightInfo")
public class FlightInfo {
	
	@Id
	@Column(name = "flightInfoId", nullable = false, unique = true)
	private String flightInfoid;
	
	private String flightNumber;
	
	private String flightType;
	
	private int numberofSeats;
	
	// @ManyToOne
	// @JoinTable(name = "flightsInfo", joinColumns = {
	// 		@JoinColumn(name = "flightInfoid", referencedColumnName = "flightInfoid") }, inverseJoinColumns = {
	// 				@JoinColumn(name = "airlineId", referencedColumnName = "airlineId") })
	private AirlineInfo airlineInfo;
    
}
