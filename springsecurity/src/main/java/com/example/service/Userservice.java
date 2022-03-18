package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Model.User;
import com.example.repo.UserRepo;



@Service
public class Userservice {

	@Autowired
	private UserRepo repository;

	public List<User> getall() {
		return repository.findAll();

	}

	public User getById(Long id) {

		return repository.findById(id).get();
	}

	public User addOrUpdate(User user) {
		return repository.save(user);

	}

	public void DeleteById(Long id) {

		repository.deleteById(id);
	}
}
