package com.tw.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);

}
