package com.billingsoftware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.billingsoftware.io.AuthRequest;
import com.billingsoftware.io.AuthResponse;
import com.billingsoftware.service.AppUserDetailsService;
import com.billingsoftware.service.UserService;
import com.billingsoftware.util.JwtUtil;

@RestController
public class AuthController {

	@Autowired
    private CategoryController categoryController;
	@Autowired
	private PasswordEncoder passwordEncoder; //springframework security
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService userService;
	

    AuthController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }
	
    @PostMapping(value = "/encode") //to encode the password for admin
	public String encodePassword(@RequestBody Map<String, String> request) {
		return passwordEncoder.encode(request.get("password"));
	}
    
    private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email or Password is incorrect");
		}
	}
    
	//login endpoint
    @PostMapping(value="/login")
	public AuthResponse login(@RequestBody AuthRequest request) throws Exception {
		authenticate(request.getEmail(),request.getPassword());
		final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail()); //get user details
		final String jwtToken= jwtUtil.generateToken(userDetails); //generate token
		//FETCH THE ROLE FROM REPOSITORY
		String role = userService.getUserRole(request.getEmail());
		return new AuthResponse(request.getEmail(), role, jwtToken);//after user login, we are generating the token. But after this we need to pass on this token to every subsequent request and then validate it
	}

}
