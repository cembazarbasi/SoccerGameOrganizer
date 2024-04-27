package com.bayareasoccerevents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bayareasoccerevents.dtos.SignupRequest;
import com.bayareasoccerevents.dtos.UserDTO;
import com.bayareasoccerevents.services.AuthService;
import jakarta.validation.Valid;

@RestController
@Validated
public class SignupUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {

        signupRequest.setFirstName(signupRequest.getFirstName());
        signupRequest.setLastName(signupRequest.getLastName());
     
        try {
            UserDTO createdUser = authService.createUser(signupRequest);
            if (createdUser == null) {
                return new ResponseEntity<>("User not created, email already exists.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {           
            return new ResponseEntity<>("User not created, an error occurred.", HttpStatus.BAD_REQUEST);
        }
    }    
}

