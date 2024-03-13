package com.bayareasoccerevents.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;


public record AuthenticationResponse(String jwtToken, String fullName) {
	
}
