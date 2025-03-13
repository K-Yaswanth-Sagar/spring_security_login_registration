package com.tw.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tw.entity.Customer;
import com.tw.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private BCryptPasswordEncoder be;
	
	@Autowired
	private CustomerRepo cr;
	
	@Override
	public boolean saveCustomer(Customer c) {
		String newpass = be.encode(c.getPassword());
		c.setPassword(newpass);
		cr.save(c);
		return cr.findById(c.getId()) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer c = cr.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException(email));
		return new User(c.getEmail(), c.getPassword(), Collections.emptyList());
	}

}
