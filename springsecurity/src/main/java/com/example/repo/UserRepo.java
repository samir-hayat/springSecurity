package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	public User getUserByUsername(String username);

}
