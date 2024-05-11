package com.paypal.payment.entites;

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
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class Seat {

    @Id
	@Column(name = "seat_number", nullable = false, unique = true)
    String seatNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    Flight flight;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    Airplane airplane;
    
    @Builder.Default
    @Column(name = "features")
    boolean isFeature = false;

    @Column(name = "created", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updatedAt;

}
