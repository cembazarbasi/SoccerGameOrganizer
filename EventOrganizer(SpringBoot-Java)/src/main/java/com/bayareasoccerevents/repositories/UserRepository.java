package com.bayareasoccerevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bayareasoccerevents.models.User;


public interface UserRepository extends JpaRepository<User, Long>{

	
	User findFirstByEmail(String email);		
	User findByfirstName(String firstName);
	User findBylastName(String lastName);
	User findByCity(String city);
	boolean existsByEmail(String email);
}
