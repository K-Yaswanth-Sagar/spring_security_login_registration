package com.tw.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmail(String email);
	
}
