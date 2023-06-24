package com.example.fruitShake.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.fruitShake.entities.Books;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkCusRequestDto {

  private String cName;
  private String cPhoneNo;
  private String cAddress;
  private OffsetDateTime createdOn;
  private OffsetDateTime updatedOn;

}
