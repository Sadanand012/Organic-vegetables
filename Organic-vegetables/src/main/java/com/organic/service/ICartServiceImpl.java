package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.CartException;
import com.organic.exception.VegetableException;
import com.organic.model.Cart;
import com.organic.model.Vegetable;
import com.organic.repository.ICartRepository;
import com.organic.repository.VegetableRepository;

@Service
public class ICartServiceImpl implements ICartService{

	@Autowired
	private ICartRepository CartRepo;
	
	@Autowired
	private VegetableRepository vegeRepo;
	
	// add to Cart
	
	@Override
	public Vegetable addToCart(Vegetable veg, Integer cartId) throws VegetableException,CartException {
		Optional<Cart> cart1= CartRepo.findById(cartId);
		Cart newCart= cart1.get();
		
		if(cart1.isPresent()) {
			List<Vegetable> lists= cart1.get().getVegetable();
			
			boolean flag = false;
			
			// chekck if vegetable is already present in the Cart or not
			for (Vegetable list : lists) {
				if(list.getVegId()==veg.getVegId()) {
					flag=true;
					break;
				}
			}
			
			// if not then add that vegetable
			if(flag==false) {
				
				Optional<Vegetable> vegeList = vegeRepo.findById(veg.getVegId());
				if(!vegeList.isPresent()) {
					throw new VegetableException("Not found vegetable");
				}else{
					Vegetable newVege= vegeList.get();
					
					if(newVege.getQuantity() > veg.getQuantity()) {
						newVege.setQuantity(newVege.getQuantity()-veg.getQuantity());
						vegeRepo.save(newVege);
						
						//adding list to vegetable list 
						lists.add(veg);
						
						// setter for vegetable list 
						newCart.setVegetable(lists);
						
						// Save to Cart 
						CartRepo.save(newCart);
					}else {
						throw new VegetableException("Only "+newVege.getQuantity()+" is Available");
					}
				}
				
				// returning here 
				return veg;
			}else {
				throw new VegetableException("Vegetable is already exist with id : "+veg.getVegId());
			}
			
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
		
	}

	@Override
	public Cart increaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException, CartException {
		Optional<Cart> cart = CartRepo.findById(cartId);
		Cart newCart= cart.get();
		
		if(cart.isPresent()) {
			List<Vegetable> lists= cart.get().getVegetable();
			
			for (Vegetable list : lists) {
				if(list.getVegId() == vegId) {
					list.setQuantity(list.getQuantity()-quantity);
				}
			}
			
			newCart.setVegetable(lists);
			Cart finalCart=CartRepo.save(newCart);
			
			return finalCart;
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
	}

	@Override
	public Cart decreaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException, CartException {
		Optional<Cart> cart = CartRepo.findById(cartId);
		Cart newCart= cart.get();
		
		if(cart.isPresent()) {
			List<Vegetable> lists= cart.get().getVegetable();
			
			for (Vegetable list : lists) {
				if(list.getVegId() == vegId) {
					list.setQuantity(list.getQuantity()+quantity);
				}
			}
			
			newCart.setVegetable(lists);
			Cart finalCart=CartRepo.save(newCart);
			
			return finalCart;
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
	}

	@Override
	public Cart removeVegetable(Integer cartId, Integer vegId) throws VegetableException, CartException {
		Optional<Cart> cart = CartRepo.findById(cartId);
		Cart newCart= cart.get();
		
		if(cart.isPresent()) {
			List<Vegetable> lists= cart.get().getVegetable();
			
			Vegetable veg= null;
			
			for (Vegetable list : lists) {
				if(list.getVegId() == vegId) {
					veg=list;
					lists.remove(veg);
					break;
				}
			}
			
			newCart.setVegetable(lists);
			Cart finalCart=CartRepo.save(newCart);
			
			return finalCart;
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
	}

	@Override
	public Cart removeAllVegetable(Integer cartId) throws CartException {
		Optional<Cart> cart = CartRepo.findById(cartId);
		if(cart.isPresent()) {
			
			cart.get().setVegetable(null);
			Cart newCart = CartRepo.save(cart.get());
			return newCart;
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
		
	}

	@Override
	public List<Vegetable> viewAllItems(Integer cartId) throws CartException {
		Optional<Cart> cart1= CartRepo.findById(cartId);
		List<Vegetable> list = null;
		if(cart1.isPresent()) {
			list =  cart1.get().getVegetable();
		}else {
			throw new CartException("Cart not found with id : "+ cartId);
		}
		return list;
	}

	


}
