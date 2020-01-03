package com.example.refuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.refuse.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	    User findByUsername(String username);

}
