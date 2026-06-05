package com.kodnest.app.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kodnest.app.entities.KodUsers;
import com.kodnest.app.repositories.KodUsersRepo;

@Service
public class KodUsersServiceImpl implements KodUsersService {

	private KodUsersRepo kodUsersRepo;
	private BCryptPasswordEncoder cryptPasswordEncoder;
//	Constructor Injection
	public KodUsersServiceImpl(KodUsersRepo kodUsersRepo) {
		super();
		this.kodUsersRepo = kodUsersRepo;
		cryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public KodUsers register(KodUsers kodUsers) {
//		Encode password and store
		String password = kodUsers.getPassword();
		String encodedPassword = cryptPasswordEncoder.encode(password);
		kodUsers.setPassword(encodedPassword);
		
		return kodUsersRepo.save(kodUsers);
	}

	@Override
	public boolean login(String username, String password) {
		KodUsers kodUsers = kodUsersRepo.findByUsername(username);
		if (kodUsers != null && cryptPasswordEncoder.matches(password, kodUsers.getPassword())) {
			return true;
		}
		return false;
	}

}
