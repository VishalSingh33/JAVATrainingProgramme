package com.airlines.british;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class BritishApplication {

	public static void main(String[] args) {
		SpringApplication.run(BritishApplication.class, args);
	}

}
