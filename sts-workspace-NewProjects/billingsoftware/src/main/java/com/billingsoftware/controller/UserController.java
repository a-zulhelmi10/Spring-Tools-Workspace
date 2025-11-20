package com.billingsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.billingsoftware.io.UserRequest;
import com.billingsoftware.io.UserResponse;
import com.billingsoftware.service.UserService;

@RestController
@RequestMapping("/admin") //means that only admin can access below methods
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse registerUser(@RequestBody UserRequest request) {
		try {
			return userService.createUser(request);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create user" + e.getMessage());
		}
	}

	@GetMapping("/users")
	public List<UserResponse> readUsers() {
		return userService.readUsers();
	}

	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") String id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
}