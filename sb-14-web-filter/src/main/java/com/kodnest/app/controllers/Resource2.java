package com.kodnest.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class Resource2 {
	
	@GetMapping("/res2")
	public String method2(HttpServletRequest req) {
		String message = (String)req.getAttribute("message"); // Downcasting
//		System.out.println("Resource came after validation.");
		return message;
	}
}
