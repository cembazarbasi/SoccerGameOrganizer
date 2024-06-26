package com.bayareasoccerevents.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;	
	private String lastName;
	private String city;
	
	@Column(unique = true)
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;
	 
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordResetToken> passwordResetTokens;

	
	
}
