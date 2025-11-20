package com.billingsoftware.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.billingsoftware.entity.UserEntity;
import com.billingsoftware.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService { //used to make authentication
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity existingUser = userRepository.findByEmail(email).orElseThrow(()-> 
		new UsernameNotFoundException("Email not found for the email: " +email));
		//return email,password,role
		return new User(existingUser.getEmail(), existingUser.getPassword(), Collections.singleton(new SimpleGrantedAuthority(existingUser.getRole()))); //use singleton because the collections expects to have several elements but we only provide one (.getrole)
	}
}
