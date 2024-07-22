package com.airlines.british.entites;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

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
// @RedisHash("flight")
public class Flight {

	@Id
	@Column(name = "flight_id", nullable = false, unique = true)
	private String flightId;

	@ManyToOne // Assuming many flights can be associated with one airplane
    @JoinColumn(name = "airplane_id") // Adjust the column name if needed
	private Airplane airplane;

	@Column(name = "origin")
	private String origin;

	@Column(name = "destination")
	private String destination;

	@Column(name = "origin_date_time")
	private LocalDateTime originDateTime;

	@Column(name = "destination_date_time")
	private LocalDateTime destinationDateTime;

	@Column(name = "duration")
	private String duration;

	@Column(name = "available_seats")
	private List<String> availbleSeats;

	@Column
	private int seatLeftToBook;

	public boolean isFullyBooked() {
		return seatLeftToBook <= 0;
	}

}
