package com.bayareasoccerevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bayareasoccerevents.models.User;


public interface UserRepository extends JpaRepository<User, Long>{

	
	User findFirstByEmail(String email);	
	User save(User user);
	User findByfirstName(String firstName);
	User findBylastName(String lastName);
	User findByCity(String city);
}
