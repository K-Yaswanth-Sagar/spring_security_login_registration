package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tw.entity.Customer;
import com.tw.service.CustomerServiceImpl;

@RestController
public class MyController {

	@Autowired
	private CustomerServiceImpl cs;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer c){
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPassword());

		Authentication authenticate = authManager.authenticate(token);
		boolean status = authenticate.isAuthenticated();
		if(status) {
			return new ResponseEntity<String>("welcome", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("wrong details", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer c){
		boolean status = cs.saveCustomer(c);
		if(status) {
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Not successful", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
