package com.example.fruitShake.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookResponceDto {

  // private String bookId;

  private String bookName;

  private String status;

}
