package com.notification.service.b2bProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "app")
@Getter
@Component
public class B2bProperties {

  private Paging paging = new Paging();
  private Allowed allowed = new Allowed();


  @Getter
  @Setter
  public static class Paging {
    private Integer messageListSize;
    private Integer chatRoomListSize;
  }

  @Getter
  @Setter
  public static class Allowed {
    private List<String> origins;
  }
  
}
