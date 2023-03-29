package com.organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.OrderNotFoundException;
import com.organic.model.Order;
import com.organic.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub

		return orderRepository.save(order);
	}

	@Override
	public Order viewOrder(Integer orderNo) throws OrderNotFoundException {
		// TODO Auto-generated method stub

		Order order = orderRepository.findById(orderNo)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found with orderNo :- " + orderNo));

		return order;
	}

	@Override
	public Order updateOrderDetails(Order order) throws OrderNotFoundException {

		Order order1 = orderRepository.findById(order.getOrderNo())
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

		return orderRepository.save(order);
	}

	@Override
	public List<Order> viewAllOrders(Integer customerId) throws OrderNotFoundException {
		// TODO Auto-generated method stub

		List<Order> orderlList = orderRepository.findByCustomerId(customerId);

		if (orderlList.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found with custid :- " + customerId);
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
}
