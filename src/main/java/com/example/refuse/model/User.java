package com.example.refuse.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
@Table(name = "user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @NotNull(message = "Phone Number is required")
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "fullName")
    private String fullName;
    @NotNull(message = "Password is required")
    @Column(name = "password")
    private String password;
}
