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
public class AddBookRequestDto {

  private String bookName;
  private String status;
  private OffsetDateTime createdOn;
  private OffsetDateTime updatedOn;

}
