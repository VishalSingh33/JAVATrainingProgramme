package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum DrinkType {

  JUICE("JUICE"),
  SHAKE("SHAKE"),
  SMOOTHIE("SMOOTHIE"),
  TEA("TEA"),
  COFFEE("COFFEE"),
  SOFTDRINK("SOFTDRINK"),
  MOCKTAIL("MOCKTAIL");


  private final String type;
  DrinkType(String type) {
    this.type = type;
  }
  public String type() {
    return type;
  }
}
