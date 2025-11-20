package com.camdrilee.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//for admin to create user
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
	private String username;
	private String email;
	private String password;
	private String role;
}
