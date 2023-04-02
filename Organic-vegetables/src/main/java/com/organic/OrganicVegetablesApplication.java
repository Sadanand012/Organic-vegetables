package com.organic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com")
public class OrganicVegetablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganicVegetablesApplication.class, args);
		
	}
}