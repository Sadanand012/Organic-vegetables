package com.organic.service;

import java.util.List;

import com.organic.exception.CustomerException;
import com.organic.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException; 
	
	public Customer updateCustomerThroughAdmin(Integer customerId,  Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String sessonKey) throws CustomerException;
	
	public Customer removeCustomer(Integer customerId) throws CustomerException; 
	
	public Customer viewCustomer(Integer customerId) throws CustomerException; 
	
	public List<Customer> viewCustomerList() throws CustomerException; 
	
}
