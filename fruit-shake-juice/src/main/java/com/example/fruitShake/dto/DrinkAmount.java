package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum DrinkAmount {

  QUATER("30 RS"),
  HALF("60 RS"),
  FULL("85 RS");

  private final String status;

  DrinkAmount(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
