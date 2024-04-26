package com.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  
  private String id;
  private String productName;
  private String entityId;
  private String entityName;
  private String brandName;
  private List<String> logo;
}
