package com.example.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) 
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public  User register(String username, String rawPassword) {
		if(userRepository.existsByUsername(username)) {
			throw new RuntimeException("Username is already exits");
		}
		User u = new User(username, passwordEncoder.encode(rawPassword));
		return userRepository.save(u);
	}
	public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userRepository.findByUsername(username)
		            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		    }
}
