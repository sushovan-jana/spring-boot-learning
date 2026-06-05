package com.kodnest.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Resource1 {
	
	@GetMapping("/res1")
	public String method1() {
		System.out.println("Resource came without any validation.");
		return "SUCCESS";
	}
}
