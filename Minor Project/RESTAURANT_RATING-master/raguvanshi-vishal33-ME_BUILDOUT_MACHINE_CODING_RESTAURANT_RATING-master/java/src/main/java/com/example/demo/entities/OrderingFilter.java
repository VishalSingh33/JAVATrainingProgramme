package com.example.demo.entities;

public class OrderingFilter {

    private int lowerLimit;
    private int UpperLimit;
    private OrderSort orderSort;

    public OrderingFilter(int lowerLimit, int upperLimit, OrderSort orderSort) {
        this.lowerLimit = lowerLimit;
        this.UpperLimit = upperLimit;
        this.orderSort = orderSort;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return UpperLimit;
    }

    public OrderSort getOrderSort() {
        return orderSort;
    }
}