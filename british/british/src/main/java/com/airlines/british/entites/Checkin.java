package com.airlines.british.entites;

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
@Table(name = "checkin")
public class Checkin {
	
	@Id
	@Column(name = "checkinId", nullable = false, unique = true)
	private String checkinId;
	
	private String seatNumber;
	
	private String gateNumber;
}