package com.kodnest.app.services;

import org.springframework.stereotype.Service;
import com.kodnest.app.entities.User;
import com.kodnest.app.repositories.UserRepo;

@Service
public class UserService {

	UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public String userResponse(String name) {
		return "Hello, " + name + " welcome, your role is User.";
	}

	public String adminResponse(String name) {
		return "Hello, " + name + " welcome, your role is Admin.";
	}

	public String getUserRole(String userName, String password) {
		
		User user = userRepo.findByUserName(userName);
//		1. Check whether user with given username exist or not
		if (user != null) {
//			2. If username exist then check the entered password same as stored password
			if (user.getPassword().equals(password)) {
				return user.getRole();
			} else return null;
			
		}
	
		return null;
	}
}
