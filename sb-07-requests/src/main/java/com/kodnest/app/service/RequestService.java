package com.kodnest.app.service;

import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
	public String executeService(String name) {
		return "Hello " + name + " Welcome to KodNest Academy.";
	}
	
	public String executeService(String fName, String mName, String lName) {
		return "Users First-Name is : " + fName + " Middle-Name is : " + mName + " Last-Name is : " + lName;
	}
	
	
	
}
