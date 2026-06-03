package com.kodnest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Sb01Basics1Application {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(Sb01Basics1Application.class, args);
		Laptop l1 = ac.getBean(Laptop.class);
		l1.laptopOs();
	}

}
