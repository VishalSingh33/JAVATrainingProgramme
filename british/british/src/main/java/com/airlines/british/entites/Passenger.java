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
	@Column(name = "passengerId", nullable = false, unique = true)
	private String passengerId;

	private String emailAddress;

	private String firstName;

	private String gender;

	private String lastName;

	private long mobileNumber;

	private long bookingId;
	
	// @OneToOne(cascade =  CascadeType.ALL)
	// @JoinColumn(name="checkinId")
	// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Checkin checkIn;
    
}
