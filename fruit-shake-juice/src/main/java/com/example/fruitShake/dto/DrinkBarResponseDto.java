package com.example.fruitShake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkBarResponseDto {

  private String dId;
  private String dType;
  private String dName;
  private String dStatus;
  private OffsetDateTime createdOn;
  private OffsetDateTime updatedOn;

}