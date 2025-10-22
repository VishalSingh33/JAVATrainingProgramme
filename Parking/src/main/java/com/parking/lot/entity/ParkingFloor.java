package com.parking.lot.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_floor")
public class ParkingFloor {

    @Id
    @Column(name = "floor_id")
    private String id;

    @Column(name = "level_number", nullable = false)
    private Integer levelNumber;

    @OneToMany(mappedBy = "floor")
    private List<Parking> spots;
}
