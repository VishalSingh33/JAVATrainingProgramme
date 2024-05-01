package com.airlines.british.entites;

import java.time.LocalDateTime;
import java.util.List;

import com.airlines.british.dto.FlightType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "airplane_id", nullable = false, unique = true)
    private String airplaneId;
	
	@Enumerated(EnumType.STRING)
    @Column
    private FlightType flightType; // ?? confusion on this code
	
	// private int numberofSeats; // should be in list or int is correct ?
	@Column(name = "all_seats")
	private List<Integer> allSeats; //ask

    // should be present or not ?
	@Column(name = "available_seats")
	private List<Integer> availbleSeats; 
	
	@ManyToOne
    @JoinColumn(name = "airline_id")
	private AirlineInfo airlineInfo;

}