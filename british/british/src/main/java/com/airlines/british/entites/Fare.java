package com.airlines.british.entites;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.RedisHash;

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
@Table(name = "fare")
// @RedisHash("fare")
public class Fare {

	@Id
	@Column(name = "fare_id", nullable = false, unique = true)
	private String fareId;

	@Column
	private LocalDateTime fareDateTime;

	@Column
	private String flightType;

	@Column
	private int fare;

	@Column
	private String bookingId;

}
