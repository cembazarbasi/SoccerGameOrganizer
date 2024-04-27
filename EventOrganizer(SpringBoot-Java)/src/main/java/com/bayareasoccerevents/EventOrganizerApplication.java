package com.bayareasoccerevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class EventOrganizerApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.config.location", "application.properties");
		SpringApplication.run(EventOrganizerApplication.class, args);
	}

}
