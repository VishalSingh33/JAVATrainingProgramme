package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum BookStatus {

  AVAILABLE("AVAILABLE"),
  BOOKED("BOOKED");

  private final String status;

  BookStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
