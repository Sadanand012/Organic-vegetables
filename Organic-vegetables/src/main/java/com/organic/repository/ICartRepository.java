package com.organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer>{
	
	
	
}
