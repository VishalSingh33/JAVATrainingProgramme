package com.example.fruitShake.dto;

import com.example.fruitShake.entities.Books;

public enum DrinkPaymentMode {

  UPI("UPI"),
  WALLETS("WALLET"),
  NETBANKING("IBANKS"),
  CASH("CASH"),
  ATMCARD("DEBIT/CREDIT");

  private final String status;

  DrinkPaymentMode(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }
}
