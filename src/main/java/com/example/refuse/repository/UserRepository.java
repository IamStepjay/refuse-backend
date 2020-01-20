package com.example.refuse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.refuse.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public Optional<User> findByPhoneNumber(String phoneNumber);


}
