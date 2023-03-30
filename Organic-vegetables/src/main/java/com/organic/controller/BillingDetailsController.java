package com.organic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.organic.model.BillingDetails;
import com.organic.service.BillingDetailsService;

@RestController
public class BillingDetailsController {

	@Autowired
	BillingDetailsService billingDetailsService;
	
	@PostMapping("/addbill")
	public ResponseEntity<BillingDetails> addBillHandler(@RequestBody BillingDetails bill){
		BillingDetails billingDetails2=billingDetailsService.addBill(bill);
		
		return new ResponseEntity<>(billingDetails2,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateBill")
	public ResponseEntity<BillingDetails> updateBillHandler(@RequestBody BillingDetails bill){
		BillingDetails billingDetails=billingDetailsService.updateBill(bill);
		return new ResponseEntity<>(billingDetails,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/viewBill/{billingId}")
	public ResponseEntity<BillingDetails> viewBillHandler(@PathVariable Integer billingId){
	BillingDetails billingDetails=billingDetailsService.viewBill(billingId);
	
	return new ResponseEntity<>(billingDetails,HttpStatus.OK);
	
	}
}
