package com.marjade.roniet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.marjade.roniet" })
public class FashionApp {

	public static void main(String[] args) {
		SpringApplication.run(FashionApp.class, args);
	}

}
