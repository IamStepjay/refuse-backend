package com.oio.wms.resources;


import com.oio.wms.domain.User;
import com.oio.wms.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api")
public class UserResource {

    private final UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResource(UserService userService,  BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User createdUser = userService.save(user);
        return ResponseEntity.created(new URI("/api/users/register")).body(createdUser);
    }
}
