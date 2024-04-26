package com.notification.service.dto;

public enum BlockedStatus {

  BLOCKED("blocked"),
  UNBLOCKED("unblocked");

  private final String status;

  BlockedStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
