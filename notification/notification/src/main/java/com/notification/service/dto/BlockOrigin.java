package com.notification.service.dto;

public enum BlockOrigin {
  
  USER("user"), 
  PAGE("page"), 
  GROUP("group");

  private final String origin;

  BlockOrigin(String origin) {
    this.origin = origin;
  }

  public String origin() {
    return origin;
  }
}
