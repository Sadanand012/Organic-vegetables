package com.organic.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.BillingDetailsNotFoundException;
import com.organic.exception.CustomerException;
import com.organic.model.Address;
import com.organic.model.BillingDetails;
import com.organic.model.Customer;
import com.organic.model.Order;
import com.organic.repository.BillingDetailsRepository;
import com.organic.repository.CustomerRepository;
import com.organic.repository.OrderRepository;

@Service
public class BillingDetailsServiceImpl implements BillingDetailsService{

	@Autowired
	BillingDetailsRepository billingDetailsRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepo;
	
//	@Override
//	public BillingDetails addBill(BillingDetails bill) {
//		// TODO Auto-generated method stub
//		
//		
//		return billingDetailsRepository.save(bill);
//	}
//
//	@Override
//	public BillingDetails updateBill(BillingDetails bill) throws BillingDetailsNotFoundException {
//		// TODO Auto-generated method stub
//		BillingDetails billingDetails=billingDetailsRepository.findById(bill.getBillingId()).orElseThrow(()->new BillingDetailsNotFoundException("Not Found"));
//		
//		billingDetailsRepository.save(bill);
//		return billingDetails;
//	}

	@Override
	public BillingDetails viewBill(Integer orderId) throws BillingDetailsNotFoundException {
		// TODO Auto-generated method stub
		
		Order existOrder = orderRepository.findById(orderId).orElseThrow(() -> new BillingDetailsNotFoundException("Order Not Found"));
		
		BillingDetails bill = new BillingDetails();
		
		bill.setOrderId(orderId);
		bill.setTransactionMode("Online/Upi");
		bill.setTransactionDate(LocalDateTime.now());
		bill.setTransactionStatus("Transation Successfull");
		
		Customer customer= customerRepo.findById(existOrder.getCustomerId()).orElseThrow(() -> new CustomerException("Customer Not Found"));
		
		bill.setBillingAddress(customer.getAddress());
		
		existOrder.setStatus("Order Placed");
		
		orderRepository.save(existOrder);
		
		billingDetailsRepository.save(bill);
		
		return bill;
	}
	
	
	

}
