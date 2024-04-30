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
	@Column(name = "flight_id", nullable = false, unique = true)
	private String flightId;
	@Column
	private Airplane airplane;
	@Column
	private String origin;
	@Column
	private String destination;
	@Column
	private LocalDateTime originDateTime;
	@Column
	private LocalDateTime destinationDateTime;
	@Column
	private Duration duration;
	@Column
	private int seatLeftToBook;

	// // @OneToOne(cascade = CascadeType.ALL)
	// // @JoinColumn(name = "fareId")
	// private int fare;   // ??

	public boolean isFullyBooked() {
		return seatLeftToBook <= 0;
	}
	// public double getFare(){
	// 	return airplane.getFlightType().getFare();
	// }

}
