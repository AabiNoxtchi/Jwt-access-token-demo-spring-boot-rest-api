package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.JwtUtil;
import com.example.demo.Service.MyUserDetailsService;

import models.AuthenticationRequest;
import models.AuthenticationResponse;

@RestController
public class HelloResource {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping( "/hello" )
	public String hello() {
		
		return "hello all";
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken
	       (@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		System.out.println("in authenticate");
		
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken( authenticationRequest.getName(),
				                                         authenticationRequest.getPasword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Ïncorrect UserName or Password !!!",e);
		}
		
		final UserDetails userDetails=userDetailsService
				.loadUserByUsername(authenticationRequest.getName());
		
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		
		System.out.println(jwt);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	

}
