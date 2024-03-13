package com.bayareasoccerevents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bayareasoccerevents.dtos.SignupRequest;
import com.bayareasoccerevents.dtos.UserDTO;
import com.bayareasoccerevents.services.AuthService;

@RestController

public class SignupUserController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
		
		signupRequest.setFirstName(signupRequest.getFirstName().toUpperCase());
		signupRequest.setLastName(signupRequest.getLastName().toUpperCase());
			
		UserDTO createdUser = authService.createUser(signupRequest);
		if(createdUser==null)
			return new ResponseEntity<>("User not created, try again later.", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
}
