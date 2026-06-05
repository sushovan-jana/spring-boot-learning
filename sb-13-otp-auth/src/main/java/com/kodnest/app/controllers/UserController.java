package com.kodnest.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kodnest.app.entities.Users;
import com.kodnest.app.services.UserService;

@Controller
@RequestMapping("/users/api")
public class UserController {
	
	UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String getSignUp() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUpImpl(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String role) {
		Users newUser = new Users(username, password, email, role);
		boolean success = userService.userSignUp(newUser);
		if (success) return "login";
		else return "invalidsignup";
	}
	
	@PostMapping("/login")
	public String userLogIn(@RequestParam String username,@RequestParam String password) {
		boolean result = userService.userSignIn(username, password);
		if (result) return "verify";
		else return "loginfail";
	}
	
	@GetMapping("/verify")
	public String verifyOtp(@RequestParam("optvalue") int otp, Model model) {
		Users ref = userService.verifyOtp(otp);
		if (ref != null) {
			model.addAttribute("username",ref.getUsername());
			return "home";
		} else {
			return "login";
		}
	}
	
}
