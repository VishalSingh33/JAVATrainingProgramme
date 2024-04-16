package com.skypro.broadband;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SkyproApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyproApplication.class, args);
	}

}
