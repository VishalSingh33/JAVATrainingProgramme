package com.notification.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {

  private String id;
  private String content;
  private List<String> mediaUrl;
      
}
