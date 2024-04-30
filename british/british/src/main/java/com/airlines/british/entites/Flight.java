package com.airlines.british.entites;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "flight")
public class Flight {

	@Id
	@Column(name = "flight_id", nullable = false, unique = true)
	private String flightId;

	@ManyToOne // Assuming many flights can be associated with one airplane
    @JoinColumn(name = "airplane_id") // Adjust the column name if needed
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
	private Time duration;

	@Column
	private int seatLeftToBook;

	// // @OneToOne(cascade = CascadeType.ALL)
	// // @JoinColumn(name = "fareId")
	// private int fare; // ??

	public boolean isFullyBooked() {
		return seatLeftToBook <= 0;
	}
	// public double getFare(){
	// return airplane.getFlightType().getFare();
	// }

}
