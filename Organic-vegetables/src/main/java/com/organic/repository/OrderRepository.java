package com.organic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.Order;

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order>findByCustomerId(Integer customerId);
}
