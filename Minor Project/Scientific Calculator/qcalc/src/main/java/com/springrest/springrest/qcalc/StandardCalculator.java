package com.springrest.springrest.qcalc;

public class StandardCalculator {

    protected double result;

    public static void getVersion() {
        System.out.println("Standard Calculator 1.0");
    }

    public double getResult() {
        return result;
    }

    public void clearResult() {
        result = 0;
    }

    public void printResult() {
        System.out.println("Standard Calculator Result:" + result);
    }

    public final void add(double num1, double num2) {
        double result = num1 + num2;
        if ((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY)) {
            throw new ArithmeticException("Addition overflow");
        }
        this.result = result;
    }

    public final void subtract(double num1, double num2) {
        result = num1 - num2;
        if ((result == Double.NEGATIVE_INFINITY) || (result == -Double.MAX_VALUE)
                || (result == Double.POSITIVE_INFINITY)) {
            throw new ArithmeticException("Subtraction overflow");
        }
        this.result = result;
        // return result;
    }

    public final void multiply(double num1, double num2) {
        result = num1 * num2;
        if ((result == Double.NEGATIVE_INFINITY) || (result == Double.MAX_VALUE)
                || (result == Double.POSITIVE_INFINITY)) {
            throw new ArithmeticException("Multiply overflow");
        }
        this.result = result;

        // return result;
    }

    public final void divide(double num1, double num2) {
        if (num2 == 0.0 || num2 == Double.NEGATIVE_INFINITY || num2 == Double.MAX_VALUE
                || num2 == Double.POSITIVE_INFINITY || num1 == Double.NEGATIVE_INFINITY
                || num1 == Double.MAX_VALUE || num1 == Double.POSITIVE_INFINITY) {
            throw new ArithmeticException("Divide overflow");
        }
        result = num1 / num2;
        // return result;
    }

}


// public void add(int num1, int num2){
// add((double)num1, (double)num2); }


