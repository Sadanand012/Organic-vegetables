package com.organic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.Customer;
import com.organic.model.Order;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findByMobileNumber(String mobileNumber);
	public List<Order>findByCustomerId(Integer customerId);
}
