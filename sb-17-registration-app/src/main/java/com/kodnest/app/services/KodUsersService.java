package com.kodnest.app.services;

import com.kodnest.app.entities.KodUsers;

public interface KodUsersService {
	public KodUsers register(KodUsers kodUsers);
	public boolean login(String username, String password);
}
