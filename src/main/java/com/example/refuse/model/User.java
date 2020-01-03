package com.example.refuse.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
	
}
