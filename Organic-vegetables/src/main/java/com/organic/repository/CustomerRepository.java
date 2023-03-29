package com.organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organic.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByMobileNumber(String mobileNumber);

}
