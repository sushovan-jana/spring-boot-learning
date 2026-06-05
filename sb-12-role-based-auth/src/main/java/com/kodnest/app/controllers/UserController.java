package com.kodnest.app.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodnest.app.dtos.UserDTO;
import com.kodnest.app.services.UserService;

@RestController
@RequestMapping("/user/api")
public class UserController {
	
	UserService service;
//	Dependency Injection
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/login")
	Map<String, String> login(@RequestBody UserDTO userDTO) {
		
		String userName = userDTO.getUserName();
		String password = userDTO.getPassword();
		
		String role = service.getUserRole(userName, password);
		String message = null;
		if (role != null) {
			if (role.trim().equalsIgnoreCase("user")) {
			    message = service.userResponse(userName);
			} else {
			    message = service.adminResponse(userName);
			}
		} else {
			message = "SOMETHING WENT WRONG, PLEASE TRY AGAIN LATER!";
		}
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("MESSAGE", message);
		
		return response;
	}
}
