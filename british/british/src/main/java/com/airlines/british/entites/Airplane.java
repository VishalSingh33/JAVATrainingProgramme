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
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "duration")
    private String duration;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "flight_number")
    private String flightNumber;
    
    @Column(name = "flight_time")
    private LocalDateTime flightTime;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fareId")
    @Column(name = "fare")
    private Fare fare;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name="flightInfoid")
    @Column(name = "flight_info")
    private FlightInfo flightInfo;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name="inventoryId")
    @Column(name = "inventory")
    private Inventory inventory;

}
