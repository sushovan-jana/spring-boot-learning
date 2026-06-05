package com.kodnest;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Laptop {
	OperatingSystem os;
	
//	CONSTRUCTOR INJECTION	
//	@Autowired
	public Laptop(OperatingSystem os) {
		this.os = os;
	}
	
	public void laptopOs() {
		os.operate();
		System.out.println("Operating System's operate() method has been called.");
	}
}
