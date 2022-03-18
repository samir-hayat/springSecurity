package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.AuthRequest;
import com.example.Model.AuthResponse;
import com.example.service.JwtUtil;
import com.example.service.UserDetailsServiceImpl;


@RestController
public class AuthenticatonController {
  

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
         
		try
		{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (authRequest.getUsername(),authRequest.getPassword()));

		}catch (UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authRequest.getUsername());
		
	     final	String token= this. jwtUtil.generateToken(userDetails);
	    return ResponseEntity.ok(new AuthResponse(token));
		}

	}
		
	

