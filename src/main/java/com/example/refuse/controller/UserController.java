package com.example.refuse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.refuse.dto.Error;
import com.example.refuse.dto.Response;
import com.example.refuse.dto.ResponseCodes;
import com.example.refuse.model.User;
import com.example.refuse.repository.UserRepository;
import com.example.refuse.util.AppUtils;
import com.example.refuse.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserRepository userRepo;
	
	@Autowired ResponseUtil responseUtil;
	
	@Autowired AppUtils appUtils;
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getUserById(@PathVariable("id") String id)
	{
		log.info("Received request to retrieve user by id: " + id);
		
		try {
			User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException());
			return responseUtil.returnSuccessResponse(user);
		}
		catch(NotFoundException e) {
			log.info("Requested resource not found with id : " + id);
			return responseUtil.returnNotFound(id, "User");
		} 
		catch(Exception e) {
			log.error("Exception while processing request: ", e);
		}
		
		return responseUtil.returnSystemError();
	}
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllUsers()
	{
		log.info("Received request to retrieve all users.");
		try {
			List<User> users = userRepo.findAll();
			if(null == users || users.isEmpty())
				throw new NotFoundException();
			return responseUtil.returnSuccessResponse(users);
		}
		catch(NotFoundException e) {
			return responseUtil.returnError(new 
					Error(ResponseCodes.NOT_FOUND, "No user found."), HttpStatus.NOT_FOUND);
		} 
		catch(Exception e) {
			log.error("Exception while processing request: ", e);
		}
		
		return responseUtil.returnSystemError();
	}
	
	@PostMapping
	public ResponseEntity<Response> createUser(@Valid @RequestBody User user, @ApiIgnore Errors errors)
	{
		
		log.info("Received request to create new user.");
		try {
			if(errors.hasErrors()) {
				return responseUtil.returnValidationErrors(errors);
			}
			if(userRepo.findByPhoneNumber(user.getPhoneNumber()).isPresent() && user.getPassword().matches(user.getPhoneNumber())) {
				return responseUtil.returnError(new 
						Error(ResponseCodes.CONFLICT, "User with same phone number already exist."), HttpStatus.CONFLICT); 
			}
			
			user = userRepo.save(user);
			if(null == user)
				throw new Exception();
			return responseUtil.returnSuccessResponse(user);
		} 
		catch(Exception e) {
			log.error("Exception while processing request: ", e);
		}
		return responseUtil.returnSystemError();
	}
	
	@DeleteMapping("/{id}")
		public String deletePost(@PathVariable String id) {

		log.info("Received request to delete user.");
	        userRepo.deleteById(id);
			return ("Deleted Successfully!!!");

	    }
		
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateClient(@PathVariable(value = "id") String id,
			  @Valid @RequestBody User application) throws NotFoundException{ 
		log.info("Received request to retrieve user application by id for update: " + id);
		
		User app = userRepo.findById(id).orElse(null);
		
		if(null == app) 
			throw new NotFoundException();
		
		if(application.getPhoneNumber() != null ) 
			app.setPhoneNumber(application.getPhoneNumber());
		
		if(application.getPassword() != null)
		app.setPassword(application.getPassword());
				
			
		final User updatedChannel = userRepo.save(app);		
	
		return responseUtil.returnSuccessResponse(updatedChannel);
		
	}
	
}
