package com.billingsoftware.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.billingsoftware.entity.UserEntity;
import com.billingsoftware.io.UserRequest;
import com.billingsoftware.io.UserResponse;
import com.billingsoftware.repository.UserRepository;

@Service
public class UserService { //implement CRUD methods
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserResponse createUser(UserRequest request){//while creating the user we have to encode the password
		UserEntity newUser  = convertToEntity(request);
		newUser = userRepository.save(newUser );
		return convertToResponse(newUser);
	}
		
	private UserResponse convertToResponse(UserEntity newUser) {
		return UserResponse.builder()
				.userId(newUser.getUserId())
				.name(newUser.getName())
				.email(newUser.getEmail())
				.role(newUser.getRole())
				.createdAt(newUser.getCreatedAt())
				.updatedAt(newUser.getUpdatedAt())
				.build();
	}

	private UserEntity convertToEntity(UserRequest request) {
		return  UserEntity.builder()
			.userId(UUID.randomUUID().toString())
			.name(request.getName())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(request.getRole())
			.build();
	}

	public String getUserRole(String email) {
		UserEntity existingUser = userRepository.findByEmail(email).orElseThrow(
				()-> new UsernameNotFoundException("User not found for the email: " +email));
		return existingUser.getRole();
	}

	public List<UserResponse> readUsers(){
		return userRepository.findAll()
			.stream()
			.map(user -> convertToResponse(user))
			.collect(Collectors.toList());
	}

	public void deleteUser(String id) {
		UserEntity existingUser = userRepository.findByUserId(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
		userRepository.delete(existingUser);
	}
}
