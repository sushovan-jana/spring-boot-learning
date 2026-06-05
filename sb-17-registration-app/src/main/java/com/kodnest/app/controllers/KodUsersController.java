package com.kodnest.app.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kodnest.app.entities.KodUsers;
import com.kodnest.app.entities.Role;
import com.kodnest.app.services.KodUsersService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class KodUsersController {
	KodUsersService kodUsersService;

	public KodUsersController(KodUsersService kodUsersService) {
		super();
		this.kodUsersService = kodUsersService;
	}

	@PostMapping("/signup")
	public Map<String, String> userSignUp(@RequestBody Map<String, String> request) {
		Map<String, String> response = new HashMap<>();

		String username = request.get("username");
		String password = request.get("password");
		int age = Integer.parseInt(request.get("age"));
		int marks = Integer.parseInt(request.get("marks"));
		Role role = Role.valueOf(request.get("role"));

		KodUsers kodUsers = new KodUsers(username, password, age, marks, role);
		KodUsers registeredUser = kodUsersService.register(kodUsers);

		if (registeredUser != null) {
			response.put("message", "REGISTRATION SUCCESSFUL!");
		} else {
			response.put("message", "REGISTRATION FAILED!");
		}

		return response;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
		boolean response = kodUsersService.login(username, password);
		if (response) {
			return ResponseEntity.ok(Map.of("Message", "LOGIN SUCCESSFUL!"));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Message", "INVALID CREDENTIALS"));
		}
	}
}
