package com.billingsoftware.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.billingsoftware.filter.JwtRequestFilter;
import com.billingsoftware.service.AppUserDetailsService;

@Configuration
@EnableWebSecurity     
public class SecurityConfig  {
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	//to configure each url which roles can access
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable) //because we use stateless jwt not csrf
			.authorizeHttpRequests(auth -> auth.requestMatchers("/login","/encode").permitAll() // the '/login' is accessible by anyone
					.requestMatchers("/categories", "/items").hasAnyRole("USER", "ADMIN") // the '/category' and '/items'only admin and user can enter
					.requestMatchers("/admin/**").hasRole("ADMIN") //the '/admin' is only accessible by admin
					.anyRequest().authenticated())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //Bcrypt is to hash passwords
	}

	@Bean
	//to restrict web pages from making requests to a different domain than the one that served the page.
	public CorsFilter corsFilter() { //Cors refers to Cross-Origin-Resource-Sharing
		return new CorsFilter(corsConfigurationSource());
	}

	private UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:4200"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(appUserDetailsService); //authenticate users according to email,pasword, and role from appUserDetailsService
	//	authProvider.setUserDetailsService(appUserDetailsService); //deprecated see the documentation
		authProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(authProvider);
	}
}
