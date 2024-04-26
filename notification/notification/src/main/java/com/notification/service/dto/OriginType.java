package com.notification.service.dto;
public enum OriginType {

  FEED("feed"),
  PRODUCT("product");

  private final String type;

  OriginType(String type) {
    this.type = type;
  }

  public String type(){
    return type;
  }
  
}
