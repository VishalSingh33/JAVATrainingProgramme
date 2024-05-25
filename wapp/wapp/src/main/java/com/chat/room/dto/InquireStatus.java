package com.chat.room.dto;

public enum InquireStatus {
  RECEIVED("RECEIVED"), 
  DELIVERED("DELIVERED");

  private final String status;

  InquireStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
