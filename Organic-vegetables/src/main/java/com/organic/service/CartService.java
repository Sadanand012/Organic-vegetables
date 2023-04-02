package com.organic.service;

import java.util.List;

import com.organic.exception.CartException;
import com.organic.exception.VegetableException;
import com.organic.model.Cart;
import com.organic.model.Vegetable;
import com.organic.model.VegetableDTO;

public interface CartService {
	
	public Cart createCart(Cart cart) throws CartException;
//	public Vegetable addToCart(Vegetable veg, Integer cartId)throws VegetableException,CartException;
	public String addToCart(Integer vegId, Integer userId) throws VegetableException, CartException;
	public Cart increaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException,CartException;
	public Cart decreaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException,CartException;
	public Cart removeVegetable(Integer cartId, Integer vegId) throws VegetableException,CartException;
	public Cart removeAllVegetable(Integer cartId) throws CartException;
	 
//	public void removeAllVegetables();
	
	public List<VegetableDTO> viewAllItems(Integer cutomerId) throws CartException;
}