package com.bayareasoccerevents.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bayareasoccerevents.dtos.HelloResponse;

@RestController
@RequestMapping("/api")

public class HelloController {
	
	@GetMapping("/hello")
	public HelloResponse hello(){
		return new HelloResponse("Hello from JWT Authorization");
	}
}
