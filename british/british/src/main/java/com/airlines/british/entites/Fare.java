package com.airlines.british.entites;

import java.time.LocalDateTime;

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
@Table(name="fare")
public class Fare {
	
	@Id
	@Column(name = "fareId", nullable = false, unique = true)
	private String fareId;

	private LocalDateTime fareDateTime;

	private double fare;
}
