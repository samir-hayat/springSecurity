package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.User;
import com.example.service.Userservice;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Userservice userservice;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public List<User> getAllUser() {

		return userservice.getall();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {

		return userservice.getById(id);
	}

	@PostMapping
	public User savaUser(@RequestBody User normaluser) {
		
		normaluser.setPassword(passwordEncoder.encode(normaluser.getPassword()));
		userservice.addOrUpdate(normaluser);
		return normaluser;

	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {

		userservice.DeleteById(id);
	}

	@PutMapping("/{id}")
	public User update(@RequestBody User user, @PathVariable long id) {

		userservice.addOrUpdate(user);
		return user;
	}

}
