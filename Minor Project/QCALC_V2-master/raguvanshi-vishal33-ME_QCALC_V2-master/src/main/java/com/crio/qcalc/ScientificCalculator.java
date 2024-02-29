package com.crio.qcalc;

public class ScientificCalculator extends StandardCalculator {

    public static void getVersion() {
        System.out.println("Scientific Calculator 1.0");
    }
    // public double getResult() {
    // return result;
    // }
    // public void clearResult() {
    // result = 0;
    // }
    // public void printResult() {
    // System.out.println("Standard Calculator Result:" + result);
    // }

    public void sin(double a) {
        result = Math.sin(a);
    }

    public void cos(double a) {
        result = Math.cos(a);
    }

    public void tan(double a) {
        result = Math.tan(a);
    }

    public void log(double a) {
        result = Math.log(a);
    }

    public void square(double a) {
        multiply(a, a);
        // result = a*a;
    }

    public void sqrt(double a) {
        result = Math.sqrt(a); // check
    }

    public void cbrt(double a) {
        result = Math.cbrt(a); // check
    }

}
