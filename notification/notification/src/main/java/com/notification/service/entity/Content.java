package com.notification.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

  private String message;
  // private String url; //in future
  private String origin_id;
  private String origin;
    
}
