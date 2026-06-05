package com.kodnest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/show")
public class DynamicController {
	int count = 0;
	@GetMapping("/counter")
	public String getMessage(Model m) {
		String content = "Internally the getMessage() -> method was called " + ++count + " times...";
		m.addAttribute("message",content);
		return "dynamic";
	}
}
