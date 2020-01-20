package com.example.refuse.model;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;
    @NotNull(message = "Password is required")
    private String password;
    private String fullName;
}
