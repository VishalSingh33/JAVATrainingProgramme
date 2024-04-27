package com.airlines.british.entites;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.airlines.british.dto.BookingStatus;

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
@Table(name = "bookingRecord")
public class BookingRecord implements Serializable
{
	private static final long serialVersionUID = -3103984818331012750L;

	@Id
	@Column(name = "bookingId", nullable = false, unique = true)
	private String bookingId;

	private LocalDateTime bookingDateTime;

	private LocalDateTime updateBookingDateTime;

	private Flight flight;
	// private String flightId;
	// private String origin;
	// private String destination;
	// private double fare;
	// private LocalDateTime originDateTime;
	// private LocalDateTime destinationDateTime;

	private BookingStatus bookingStatus;

	private int seatNumber;

	private int bookingFare;
	
	// @OneToMany(cascade = CascadeType.ALL)
	// @JoinTable(name="bookingDetails", joinColumns = {@JoinColumn(name="bookingId")} , inverseJoinColumns = {@JoinColumn(name="passengerId")})
	private List<Passenger> passengers;
	
    
}
