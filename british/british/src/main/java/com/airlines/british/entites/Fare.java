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
@Table(name="fare")
public class Fare {
	
	@Id
	@Column(name = "fareId", nullable = false, unique = true)
	private String fareId;

	private String currency;
	
	private double fare;
}
