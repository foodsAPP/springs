package com.example.demo.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Users;

public interface UsersRepository  extends MongoRepository<Users,String>{
    public Users findFristByUsersname(String username);
    public Users findOneByUsersnameAndPassword(String u,String p);
	
}
