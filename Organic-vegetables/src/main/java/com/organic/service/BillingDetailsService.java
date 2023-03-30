package com.organic.service;



import com.organic.exception.BillingDetailsNotFoundException;
import com.organic.model.BillingDetails;

public interface BillingDetailsService {

	public BillingDetails addBill(BillingDetails bill);
	
	
	public BillingDetails updateBill(BillingDetails bill)throws BillingDetailsNotFoundException;
	
	public BillingDetails viewBill(Integer billingId) throws BillingDetailsNotFoundException;
	
	
}
