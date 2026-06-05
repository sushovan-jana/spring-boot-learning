package com.kodnest.app.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodnest.app.entities.User;
import com.kodnest.app.services.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

	LoginService loginService;

	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@PostMapping("/auth/login")
	public Map<String, String> login(@RequestBody Map<String, String> request) {
		
		Map<String, String> response = new HashMap<String, String>();
		try {
//			Collect from frontend
			String username = request.get("username");
			String password = request.get("password");
			
//			Store in map to send to frontend
			User user = loginService.authenticate(username, password);
			
			response.put("username", user.getUsername());
			response.put("success", "Login Successful");
			
			return response;
		} catch (Exception e) {
			response.put("username", null);
			response.put("FAILED", e.getMessage());
			return response;
		}

	}
}
