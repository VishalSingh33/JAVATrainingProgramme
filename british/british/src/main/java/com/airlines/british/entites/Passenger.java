package com.airlines.british.entites;

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
@Table(name = "passenger")
public class Passenger {

	@Id
	@Column(name = "passenger_id", nullable = false, unique = true)
	private String passengerId;

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	//many to one to fetch all bookingDetails
	@Column
	private String bookingId;

	@Column
	private String fareId;

	@Column(name = "created", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updatedAt;

}
