package com.bayareasoccerevents.services;

import com.bayareasoccerevents.dtos.UserDTO;
import com.bayareasoccerevents.models.User;
import com.bayareasoccerevents.dtos.SignupRequest;


public interface AuthService {

	UserDTO createUser(SignupRequest signupRequest);
	
	User findByEmail(String email);

	 void updatePassword(User user, String newPassword);

	

	
	}


