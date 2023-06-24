package com.example.fruitShake.dto;

import java.time.OffsetDateTime;
import com.example.fruitShake.entities.Books;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkBarRequestDto {

  private String dType;
  private String dName;
  private String dStatus;
  private OffsetDateTime createdOn;
  private OffsetDateTime updatedOn;

}


// @NotBlank(message = "Enter enity city")
//   @Size(min = 2, message = "Minimum 2 character required")
//   @Size(max = 100, message = "Maximum 100 character allowed")
//   private String city;

//   @Pattern(regexp = "(^$|^([0-9]{6,}))", message = "Number is invalid")
//   private String contactNo1;

//   @Pattern(regexp = "^(\\d{10})", message = "Number is invalid")
//   private String contactNo2;

//   @NotBlank(message = "Enter entity email")
//   @Email(message = "Email is invalid")
//   private String email;

//   @ValidateSalesSet
//   private Set<String> sales;

//   @ValidateBuysSet
//   private Set<String> buys;
