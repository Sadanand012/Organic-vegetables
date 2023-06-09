package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.CustomerException;
import com.organic.model.CurrentUserSession;
import com.organic.model.Customer;
import com.organic.repository.CustomerRepository;
import com.organic.repository.UserSessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserSessionRepo currentUserSesson;
	

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
		// First we will check given customer is already registered with same mobile no or not
		Customer existingCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
//		System.out.println(existingCustomer);
		if(existingCustomer != null) throw new CustomerException("This Mobile number is already registered");
		
		return customerRepository.save(customer);
			
		
	}
	
	@Override
	public Customer updateCustomer(Customer customer, String sessionKey) throws CustomerException {
		
		CurrentUserSession loggedInUser = currentUserSesson.findByUuid(sessionKey);
		
		if(loggedInUser == null) throw new CustomerException("Please provide a valid key to update a customer");
		
		if(customer.getCustomerId() == loggedInUser.getUserId()) {
			return customerRepository.save(customer);
		}
		
		throw new CustomerException("Invalid customer details, please login first");
	}
	
	

	@Override
	public Customer updateCustomerThroughAdmin(Integer customerId, Customer customer) throws CustomerException {
		//check whether customer is registered or not with the given id
		Optional<Customer> opt = customerRepository.findById(customerId);
		if(opt.isPresent()) {
			Customer registeredCustomer = opt.get();
			if(registeredCustomer.getCustomerId() != customerId) throw new CustomerException("Customer id should be "+ customerId);
			
			return customerRepository.save(customer);
		}
		throw new CustomerException("Invalid Customer id");
	}

	@Override
	public Customer removeCustomer(Integer customerId) throws CustomerException {
		//check whether customer is registered or not with the given id
		Optional<Customer> opt = customerRepository.findById(customerId);
		if(opt.isPresent()) {
			Customer registeredCustomer = opt.get();
			
			customerRepository.delete(registeredCustomer);
			
			return registeredCustomer;
		}
		throw new CustomerException("Invalid Customer id");
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> opt = customerRepository.findById(customerId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CustomerException("No any customer registered with this id");
	}

	@Override
	public List<Customer> viewCustomerList() throws CustomerException {
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) throw new CustomerException("No any customer found");
		
		return customers;
	}

	

}
