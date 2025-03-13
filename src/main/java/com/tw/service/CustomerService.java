package com.tw.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tw.entity.Customer;

public interface CustomerService extends UserDetailsService {

	boolean saveCustomer(Customer c);
}
