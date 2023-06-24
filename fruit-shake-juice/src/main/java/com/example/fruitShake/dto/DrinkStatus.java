package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum DrinkStatus {

  AVAILABLE("AVAILABLE"),
  STOCKOUT("STOCK-OUT");

  private final String status;

  DrinkStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
