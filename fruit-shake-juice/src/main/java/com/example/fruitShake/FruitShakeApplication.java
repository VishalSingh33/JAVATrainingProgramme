package com.example.fruitShake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.fruitShake", "com.example.fruitShake.mapper"})
public class FruitShakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitShakeApplication.class, args);
	}

}
