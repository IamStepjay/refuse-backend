package com.example.refuse.repository;

import java.util.Optional;
import javax.persistence.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.refuse.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public Optional<User> findByPhoneNumber(String phoneNumber);


}
