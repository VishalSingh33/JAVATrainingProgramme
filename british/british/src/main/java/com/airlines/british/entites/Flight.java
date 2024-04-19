package com.airlines.british.entites;

import java.time.Duration;
import java.time.LocalDateTime;
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
@Table(name = "flight")
public class Flight {

	@Id
	@Column(name = "flightId", nullable = false, unique = true)
	private String flightId;

	private Airplane airplane;

	private String origin;

	private String destination;

	private LocalDateTime originDateTime;

	private LocalDateTime destinationDateTime;

	private Duration duration;

	private int seatLeftToBook;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "fareId")
	private double fare;

	public boolean isFullyBooked() {
		return seatLeftToBook <= 0;
	}

}
