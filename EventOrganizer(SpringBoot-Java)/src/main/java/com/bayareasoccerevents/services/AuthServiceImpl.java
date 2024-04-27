package com.bayareasoccerevents.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bayareasoccerevents.dtos.SignupRequest;
import com.bayareasoccerevents.dtos.UserDTO;
import com.bayareasoccerevents.models.User;
import com.bayareasoccerevents.repositories.UserRepository;

@Service
public class AuthServiceImpl implements  AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
    public UserDTO createUser(SignupRequest signupRequest) {
		
       
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setCity(signupRequest.getCity());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setFirstName(createdUser.getFirstName());
        userDTO.setLastName(createdUser.getLastName());

        return userDTO;
    }
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id){
		return userRepository.findById((long) id).orElse(null);
	}
	
	public User getUserByEmail(String email){
		return userRepository.findFirstByEmail(email);
	}	
		
	public String deleteUser(int id) {
		userRepository.deleteById((long) id);
		return "user removed!! "+id;
	}		

    @Override
    public User findByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public void updatePassword(User user, String newPassword) {       
        user.setPassword(passwordEncoder.encode(newPassword)); 
        userRepository.save(user);
    }    
    
    
}
