package com.parking.lot.entity;

import com.parking.lot.enums.GateType;

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
@Table(name = "gate")
public class Gate {

  @Id
  private String id;

  @Enumerated(EnumType.STRING)
  private GateType gateType;

  // If you also want a visible gate number (distinct from PK)
  private Integer gateNumber;
}
