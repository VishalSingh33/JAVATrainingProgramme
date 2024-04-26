package com.airlines.british.dto;

public enum FlightType {
  Economy(4000), 
  PremiumEconomy(6500), 
  Business(10000);

  private final int fare;

  FlightType(int fare) {
      this.fare = fare;
  }

  public int getFare() {
      return fare;
  }
}
