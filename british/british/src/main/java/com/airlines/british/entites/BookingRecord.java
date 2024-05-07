package com.airlines.british.entites;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.airlines.british.dto.BookingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "booking_record")
public class BookingRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "booking_id", nullable = false, unique = true)
	private String bookingId;
	
	@ManyToOne
    @JoinColumn(name = "flight_id")
	private Flight flight;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
	private BookingStatus bookingStatus;
	
	@Column(name = "seat_number")
    private String seatNumber;
    
    @Column(name = "booking_fare")
    private int bookingFare;
    
    @Column(name = "passenger_id")
	private String passengerId;

	@Column
	private LocalDateTime bookingDateTime;
	
	@Column
	private LocalDateTime updateBookingDateTime;
	
    
}
