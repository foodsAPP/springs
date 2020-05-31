package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	public User findFirstByUsername(String username);
	
	public User findOneByUsernameAndPassword(String u, String p);
}
