package com.kodnest.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kodnest.app.service.RequestService;

@Controller
@RequestMapping("/api")
public class RequestController {
	RequestService service;
	
//  Constructor injection..	
	public RequestController(RequestService service) {
		System.out.println("Object created...");
		this.service = service;
	}
	
	int count = 0;
	@GetMapping("/show")
	public String getMessage(Model model) {
		String content = service.serviceMessage(++count);
		model.addAttribute("message", content);
		return "page";
	}
	
}
