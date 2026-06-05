package com.kodnest.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kodnest.app.entities.KodUsers;

@Repository
public interface KodUsersRepo extends JpaRepository<KodUsers, Integer> {
	public KodUsers findByUsername(String username);
}
