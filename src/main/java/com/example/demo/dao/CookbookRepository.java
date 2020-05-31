package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Cookbook;



public interface CookbookRepository extends MongoRepository<Cookbook,String>{
	public List<Cookbook> findByCommentsDtAfter(LocalDateTime thedt);
	List <Cookbook> findByTitle(String title);
}
