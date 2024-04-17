package com.airlines.british.entites;

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
@Table(name="airlineInfo")
public class AirlineInfo {
	
	@Id
	@Column(name = "airlineId", nullable = false, unique = true)
	private String airlineId;
	
	private String airlineLogo;

	private String nameOfAirline;
    
}
