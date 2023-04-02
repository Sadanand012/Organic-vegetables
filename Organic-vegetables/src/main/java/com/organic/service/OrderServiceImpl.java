package com.organic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.CustomerException;
import com.organic.exception.OrderNotFoundException;
import com.organic.model.Cart;
import com.organic.model.CurrentUserSession;import com.organic.model.Customer;
import com.organic.model.Order;
import com.organic.model.VegetableDTO;
import com.organic.repository.CartRepository;
import com.organic.repository.CustomerRepository;
import com.organic.repository.OrderRepository;
import com.organic.repository.UserSessionRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserSessionRepo currentUserSession;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CartRepository cartRepository;
	@Override
	public Order addOrder(Integer customerId) {
		
		
		
		CurrentUserSession loggedInUser = currentUserSession.findById(customerId).orElseThrow(()-> 
										new CustomerException("Please provide a valid customerId or Login First"));
		Order newOrder = new Order();
		if(customerId == loggedInUser.getUserId()) {
			
			Cart cartDetailes= cartRepository.findByCustomerId(customerId);
			
			List<VegetableDTO> vegList= cartDetailes.getVegetable();
			
			Double totalPrice= 0.00;
			
			for(VegetableDTO list: vegList) {
				
				totalPrice += list.getPrice() * list.getQuantity();
				
			}
			
			newOrder.setCustomerId(customerId);
			newOrder.setDate(LocalDate.now());
			newOrder.setTotalAmount(totalPrice);
		
			newOrder.setStatus("waiting for billing");
			
			return orderRepository.save(newOrder);
			
		}
		else
		throw new CustomerException("Invalid customer details, please login first");
		
		
	}

	@Override
	public Order viewOrder(Integer orderNo) throws OrderNotFoundException {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderNo)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found with orderNo :- " + orderNo));

		return order;
	}


	@Override
	public List<Order> viewAllOrders(Integer customerId) throws OrderNotFoundException {
		// TODO Auto-generated method stub

		List<Order>orderlList=customerRepository.findByCustomerId(customerId);
		if (orderlList.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found With customerId "+customerId);
		}

	
		return orderlList;
	}

	@Override
	public List<Order> viewOrderList() throws OrderNotFoundException {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findAll();
		if (orders.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found ");
		}
		return orders;
	}

	@Override
	public Order cancelOrder(Integer orderNo) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderNo)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found with orderNo :- " + orderNo));
		orderRepository.delete(order);
		return order;
	}

	@Override
	public List<Order> viewAllOrdersByDate(LocalDate date) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		List<Order>orders=orderRepository.viewAllOrdersByDate(date);
		if (orders.isEmpty()) {
			throw new OrderNotFoundException("Order not Found");
		}
		
		return orders;
	}
}
