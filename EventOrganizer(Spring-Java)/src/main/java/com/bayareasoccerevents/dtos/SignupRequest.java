package com.bayareasoccerevents.dtos;

import lombok.Data;

@Data
public class SignupRequest {
	
	private String firstName;	
	private String lastName;
	private String city;
	private String email;
	private String password;
	
}
