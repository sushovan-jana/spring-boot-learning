package com.kodnest.app.services;

import org.springframework.stereotype.Service;
import com.kodnest.app.entities.User;
import com.kodnest.app.repositories.UserRepo;

@Service
public class LoginService {
	UserRepo repo;
//	Constructor Injection...
	public LoginService(UserRepo repo) {
		super();
		this.repo = repo;
	}
	
	public User authenticate(String username, String password) {
		User user = repo.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("Invalid Credentials!");
	}
	
}
