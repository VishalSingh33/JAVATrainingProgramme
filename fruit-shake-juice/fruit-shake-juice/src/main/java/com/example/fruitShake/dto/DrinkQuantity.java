package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum DrinkQuantity {

  QUATER("150ML"),
  HALF("330ML"),
  FULL("725ML");

  private final String status;

  DrinkQuantity(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
