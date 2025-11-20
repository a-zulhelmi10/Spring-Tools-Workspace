package com.billingsoftware.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.billingsoftware.service.AppUserDetailsService;
import com.billingsoftware.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get the token from the header first
		final String authorizationHeader = request.getHeader("Authorization"); //Authorization will be the header which will give us jwt token along with the bearer keyword
		
		String email = null;
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			//extract the token only from the bearer
			jwt = authorizationHeader.substring(7);
			email = jwtUtil.extractUsername(jwt);
		}
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//set the authentication
			UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);
			if(jwtUtil.validateToken(jwt,  userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
						(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
