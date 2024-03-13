package com.bayareasoccerevents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bayareasoccerevents.dtos.AuthenticationRequest;
import com.bayareasoccerevents.dtos.AuthenticationResponse;
import com.bayareasoccerevents.models.User;
import com.bayareasoccerevents.services.AuthServiceImpl;

import com.bayareasoccerevents.services.jwt.UserDetailsServiceImpl;
import com.bayareasoccerevents.utils.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
			
			
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect Username or Password.");
		}catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created. Register User first");
			return null;
		}
		User user = authServiceImpl.getUserByEmail(authenticationRequest.getEmail());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		String fullName = user.getFirstName() + " " + user.getLastName().substring(0, 1);
		return new AuthenticationResponse(jwt, fullName);
	}
	
	
	 @PostMapping("/logout")
	    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
	        // Extract token from the Authorization header
	        String jwtToken = token.substring("Bearer ".length());

	        // Check if the token is already invalidated
	        if (jwtUtil.isTokenInvalid(jwtToken)) {
	            return ResponseEntity.badRequest().body("Invalid token");
	        }

	        // Invalidate the token
	        jwtUtil.invalidateToken(jwtToken);

	        // Clear the security context
	        SecurityContextHolder.clearContext();

	        return ResponseEntity.ok("Logout successful");
	    }
	
	
}
