package com.bayareasoccerevents.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bayareasoccerevents.models.Event;
import com.bayareasoccerevents.models.User;
import com.bayareasoccerevents.services.AuthServiceImpl;

@RestController

public class UserController {
	
	@Autowired
	private AuthServiceImpl userService;
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return userService.getUsers();
	}		
	
	@GetMapping("/userById/{id}")
	public User findById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/user/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
}
