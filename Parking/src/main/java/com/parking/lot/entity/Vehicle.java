package com.parking.lot.entity;

import com.parking.lot.enums.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@Table(name = "vehicle")
public class Vehicle {
    
  @Id 
  private String vehicleNumber;   // use as natural key

  @Enumerated(EnumType.STRING)
  private VehicleType vehicleType;
}
