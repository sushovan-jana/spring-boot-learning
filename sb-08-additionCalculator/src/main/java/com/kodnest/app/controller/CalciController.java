package com.kodnest.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.app.service.CalciService;

@Controller
@RequestMapping("/api")
public class CalciController {
	
	CalciService service;
	
//	Constructor Injection	
	public CalciController(CalciService service) {
		this.service = service;
	}
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
//	Method to getResult only, operation will be done in service
	@GetMapping("/addDigits")
	public String getResult(@RequestParam int a,@RequestParam int b, Model model) {
		int result = service.calculateResult(a, b);
		model.addAttribute("sum", result);
		return "result";
	}
	
}









