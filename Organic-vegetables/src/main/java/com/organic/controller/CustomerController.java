package com.organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organic.model.Customer;
import com.organic.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@RequestBody Customer customer){
		
		Customer registeredCustomer = customerService.addCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @PathVariable("key") String sessionKey){
		
		Customer updatedCustomer = customerService.updateCustomer(customer, sessionKey);
		
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}
	
	@PutMapping("/customerupdatebyadmin/{id}")
	public ResponseEntity<Customer> updateCustomerThroughAdminHandler(@PathVariable("id") Integer customerId, @RequestBody Customer customer){
		
		Customer updatedCustomer = customerService.updateCustomerThroughAdmin(customerId, customer);
		
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> removeCustomer(@PathVariable("id") Integer customerId){
		
		Customer removedCustomer = customerService.removeCustomer(customerId);
		
		return new ResponseEntity<>(removedCustomer, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") Integer customerId){
		
		Customer existingCustomer = customerService.viewCustomer(customerId);
		
		return new ResponseEntity<>(existingCustomer, HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<Customer>> viewCustomerList(){
		
		List<Customer> customers = customerService.viewCustomerList();
		
		return new ResponseEntity<>(customers, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
