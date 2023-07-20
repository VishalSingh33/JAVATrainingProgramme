package com.springrest.springrest.qcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QcalcApplication {

	public static void main(String[] args) {
		// SpringApplication.run(QcalcApplication.class, args);

		System.out.println("Starting QCalc..");

		LogicCalculator calc = new LogicCalculator();
		calc.OR(8, 6);
		calc.printResult();
		// StandardCalculator calc = new StandardCalculator();
		// calc.add(5.0, 5.0);
		// calc.printResult();
		// calc.subtract(Double.MAX_VALUE, Double.MAX_VALUE);
		// calc.printResult();
		// calc.multiply(Double.MAX_VALUE, Double.MAX_VALUE);
		// calc.printResult();
		// calc.divide(Double.MAX_VALUE, Double.MAX_VALUE);
		// calc.printResult();
		// calc.multiply(2.2, 2);
		// calc.printResult();
		// try {
		// calc.divide(10, 2.5);
		// calc.printResult();
		// } catch (ArithmeticException e) {
		// System.out.println("Error: " + e.getMessage());
		// }
		// // Clear the result
		// calc.clearResult();
		// calc.printResult();
		// System.out.println("Result is here: " + calc.getResult());

	}
}
