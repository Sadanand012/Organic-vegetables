package com.organic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.organic.model.Order;

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
//	@Query("select o from order o where o.customerId=?1")
//	public List<Order>findByCustomerId(Integer customerId);
	@Query("Select o from Order o where o.date=?1")
	public List<Order>viewAllOrdersByDate(LocalDate date);
}
