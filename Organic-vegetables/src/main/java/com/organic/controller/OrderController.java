package com.organic.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organic.model.Order;
import com.organic.service.OrderService;

@RestController
public class OrderController {

	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/addorder/{customerId}")
	public ResponseEntity<Order> addOrderHandler(@PathVariable Integer customerId) {
		Order order2=orderService.addOrder(customerId);
		
		return new ResponseEntity<>(order2,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewOrder/{orderNo}")
	public ResponseEntity<Order>  viewOrderHandler(@PathVariable Integer orderNo) {
		Order order=orderService.viewOrder(orderNo);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewAll/{customerId}")
	public ResponseEntity<List<Order>>viewAllOrdersHandler(@PathVariable Integer customerId){
		
		List<Order>list=orderService.viewAllOrders(customerId);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	

	
	@GetMapping("/viewOrderList")
	public ResponseEntity<List<Order>>viewOrderListHandler(){
		List<Order>list=orderService.viewOrderList();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/viewOrderListDate/{date}")
	public ResponseEntity<List<Order>>viewAllOrdersByDate(@PathVariable	LocalDate date){
		List<Order>list=orderService.viewAllOrdersByDate(date);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelOrder/{orderNo}")
	public ResponseEntity<Order>  cancelOrderHandler(@PathVariable Integer orderNo) {
	
	Order order=orderService.cancelOrder(orderNo);
	
	return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	
}
