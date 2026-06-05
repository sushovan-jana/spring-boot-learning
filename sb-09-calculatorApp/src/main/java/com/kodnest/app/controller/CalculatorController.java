package com.kodnest.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.app.service.CalculatorService;

@Controller
@RequestMapping("/api")
public class CalculatorController {

	CalculatorService service;

//  Constructor Injection	
	public CalculatorController(CalculatorService service) {
		this.service = service;
	}

	@GetMapping("/home")
	public String index() {
		return "index";
	}

	@GetMapping("/calculate")
	public String calculate(@RequestParam("num1") int n1, @RequestParam("num2") int n2, String operation, Model model) {
		switch (operation) {
			case "ADD" -> {
				int content = service.add(n1, n2);
				model.addAttribute("result", content);
			}
			case "SUBTRACT" -> {
				int content = service.sub(n1, n2);
				model.addAttribute("result", content);
			}
			case "DIVISION" -> {
				if (n2 == 0) {
					model.addAttribute("result", "Can't divide by zero");
				} else {
					int div = service.div(n1, n2);
					model.addAttribute("result", div);
				}
			}
			case "MULTIPLICATION" -> {
				int content = service.mul(n1, n2);
				model.addAttribute("result", content);		
			}
			default -> {
	            model.addAttribute("result", "Invalid Operation");
	        }
		}
		return "result";
	}
}
