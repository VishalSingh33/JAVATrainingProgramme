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
@Table(name = "airline_info")
public class AirlineInfo {

	@Id
	@Column(name = "airline_id", nullable = false, unique = true)
	private String airlineId;
	
	@Column
	private String airlineLogo;
	
	@Column
	private String nameOfAirline;

}
