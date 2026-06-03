package com.kodnest.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/greeting")
public class Request {

	@GetMapping("/greet")
	@ResponseBody
	public String greet() {
		return "Hello World";
	}
}
