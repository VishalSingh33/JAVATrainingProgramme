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
@Table(name="flight")
public class Flight {
	
	@Id
	@Column(name = "flightId", nullable = false, unique = true)
	private String flightId;	

	private String destination;

	private String duration;
	
	private LocalDateTime flightDate;
	
	private String flightNumber;
	
	private LocalDateTime flightTime; 

	private String origin;
	
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "fareId")
	private Fare fare;
	
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="flightInfoid")
	private FlightInfo flightInfo;
	
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="inventoryId")
	private Inventory inventory;
    
}
