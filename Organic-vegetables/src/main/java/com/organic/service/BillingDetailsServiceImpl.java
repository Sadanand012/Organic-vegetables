package com.organic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.BillingDetailsNotFoundException;
import com.organic.model.BillingDetails;
import com.organic.repository.BillingDetailsRepository;

@Service
public class BillingDetailsServiceImpl implements BillingDetailsService{

	@Autowired
	BillingDetailsRepository billingDetailsRepository;
	
	@Override
	public BillingDetails addBill(BillingDetails bill) {
		// TODO Auto-generated method stub
		
		
		return billingDetailsRepository.save(bill);
	}

	@Override
	public BillingDetails updateBill(BillingDetails bill) throws BillingDetailsNotFoundException {
		// TODO Auto-generated method stub
		BillingDetails billingDetails=billingDetailsRepository.findById(bill.getBillingId()).orElseThrow(()->new BillingDetailsNotFoundException("Not Found"));
		
		billingDetailsRepository.save(bill);
		return billingDetails;
	}

	@Override
	public BillingDetails viewBill(Integer billingId) throws BillingDetailsNotFoundException {
		// TODO Auto-generated method stub
		BillingDetails billingDetails=billingDetailsRepository.findById(billingId).orElseThrow(()->new BillingDetailsNotFoundException("Not Found"));
		
		
		return billingDetails;
	}

}
