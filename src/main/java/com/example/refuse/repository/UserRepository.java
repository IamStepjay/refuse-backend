package com.example.refuse.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.refuse.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByPhoneNumber(String phoneNumber);


}
