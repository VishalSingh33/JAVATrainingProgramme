package com.airlines.british.entites;

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
@Table(name = "passenger")
public class Passenger {

	@Id
	@Column(name = "passenger_id", nullable = false, unique = true)
	private String passengerId;
	@Column
	private User user;
	@Column
	private String bookingId;
	@Column
	private String fareId;
	
}
