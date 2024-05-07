package com.airlines.british.entites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "airplane_id", nullable = false, unique = true)
    private String airplaneId;
	
	// @Column(name = "all_seats")
    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL)
	private List<Seat> allSeats;

	@ManyToOne
    @JoinColumn(name = "airline_id")
	private AirlineInfo airlineInfo;

}