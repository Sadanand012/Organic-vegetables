package com.organic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.organic.exception.OrderNotFoundException;
import com.organic.model.Order;

@Service
public interface OrderService {

	public Order addOrder(Order order);
	
	public Order viewOrder(Integer orderNo)throws OrderNotFoundException;
	
	public Order updateOrderDetails(Order order) throws OrderNotFoundException;
	
	public List<Order>viewAllOrders(Integer orderNo)throws OrderNotFoundException;
	
	public List<Order>viewAllOrdersByDate(LocalDate date)throws OrderNotFoundException;
	
	public List<Order>viewOrderList()throws OrderNotFoundException;
	
	public Order cancelOrder(Integer orderNo) throws OrderNotFoundException;
	
}
