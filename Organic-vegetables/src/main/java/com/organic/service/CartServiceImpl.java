package com.organic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.CartException;
import com.organic.exception.CustomerException;
import com.organic.exception.VegetableException;
import com.organic.model.Cart;
import com.organic.model.Customer;
import com.organic.model.Vegetable;
import com.organic.model.VegetableDTO;
import com.organic.repository.CartRepository;
import com.organic.repository.CustomerRepository;
import com.organic.repository.VegetableRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository CartRepo;
	
	@Autowired
	private VegetableRepository vegeRepo;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Create Cart 
	
	@Override
	public Cart createCart(Cart cart) throws CartException {
		Cart create= CartRepo.save(cart);
		
		if(create==null) {
			throw new CartException("Cart Not Created");
		}else {
			return create;
		}
	}

	
	// add to Cart

	@Override
	public String addToCart(Integer vegId, Integer userId) throws VegetableException, CartException {
		
		// check customer id is valid or not
		Optional <Customer> existingCustomerOpt = customerRepository.findById(userId);
		
		if(existingCustomerOpt.isEmpty()) throw new CustomerException("Invalid customer id");
		
		Customer loggedInCustomer = existingCustomerOpt.get();
		// fetching cart by customer id
		Cart customerCart= CartRepo.findByCustomerId(userId);
		
		if(customerCart == null) {
			// cart creating
			customerCart = new Cart();
			customerCart.setCustomerId(userId);
			List<VegetableDTO> vegList = new ArrayList<>();
			customerCart.setVegetable(vegList);
		}

		
		// fetching vegetable list from cart
		List<VegetableDTO> customerCartVegList = customerCart.getVegetable();
		
		// step to check weather vegetable with this vegId is available in DB or not
		Optional<Vegetable> vegObjOpt = vegeRepo.findById(vegId);
		
		if(vegObjOpt.isEmpty()) throw new VegetableException("Invalid vegetable id");
		
		// if vegetable is available in DB then getting object of that particular vegetable
		Vegetable vegetable = vegObjOpt.get(); 
		
		// checking if this vegetable already available in cart or not
		if(customerCartVegList.stream().anyMatch(v -> v.getVegId() == vegId)) throw new VegetableException("already available in cart");
			
		// if not available in cart then add to cart
		
		// check available quantity in DB
		Integer availableQty = vegetable.getQuantity();
		// creating temporary vegetable object to set quantity 1
		VegetableDTO vegetableDTO = new VegetableDTO();
		vegetableDTO.setVegId(vegId);
		vegetableDTO.setName(vegetable.getName());
		vegetableDTO.setPrice(vegetable.getPrice());
		
		
		if(availableQty > 0) {

			vegetableDTO.setQuantity(1);
			
			customerCartVegList.add(vegetableDTO);
			
			// now updated vegList is set to the customer cart
			customerCart.setVegetable(customerCartVegList);
			
			// update quantity in DB
			vegetable.setQuantity(availableQty - 1);
			
			vegeRepo.save(vegetable);
		}
		
		// now updated vegList is set to the customer cart
		customerCart.setVegetable(customerCartVegList);
		
		CartRepo.save(customerCart);
			
		return vegetableDTO.getName() + " is added to cart";
		
		
	}


	@Override
	public Cart increaseVegQantity(Integer customerId, Integer quantityToIncrease,Integer vegId) throws VegetableException, CartException {

		// fetching cart by customer id
		Cart customerCart= CartRepo.findByCustomerId(customerId);
		
		if(customerCart == null) throw new CustomerException("Invalid Customer id");
		
		// fetching vegetable list from cart
		List<VegetableDTO> customerCartVegList = customerCart.getVegetable();
		
		// step to check weather vegetable with this vegId is available in DB or not
		Optional<Vegetable> vegObjOpt = vegeRepo.findById(vegId);
		
		if(vegObjOpt.isEmpty()) throw new VegetableException("Invalid vegetable id");
		
		// if available in DB then getting object of that particular vegetable
		Vegetable vegetable = vegObjOpt.get(); 
		
		// checking if this vegetable already available in cart or not
		if(!customerCartVegList.stream().anyMatch(v -> v.getVegId() == vegId)) throw new VegetableException("this vegetable not found in cart");
			
		// if available in cart then add to cart
		// check available quantity in DB
		Integer availableQty = vegetable.getQuantity();
	
		
		if(availableQty >= quantityToIncrease) {
	
			for (VegetableDTO veg : customerCartVegList) {
				if(veg.getVegId() == vegId) {
					veg.setQuantity(veg.getQuantity()+ quantityToIncrease);
				}
			}

			// now updated vegList is set to the customer cart
			customerCart.setVegetable(customerCartVegList);
			
			// update quantity in DB
			vegetable.setQuantity(vegetable.getQuantity() - quantityToIncrease);
			
			vegeRepo.save(vegetable);
		}else {
			throw new VegetableException("Only "+availableQty+" available in Cart");
		}
		
		return customerCart;
	
	}

	@Override
	public Cart decreaseVegQantity(Integer customerId, Integer quantityToDecrease, Integer vegId) throws VegetableException, CartException {
		// fetching cart by customer id
		Cart customerCart= CartRepo.findByCustomerId(customerId);
		
		if(customerCart == null) throw new CustomerException("Invalid Customer id");
		
		// step to check weather vegetable with this vegId is available in DB or not
		Optional<Vegetable> vegObjOpt = vegeRepo.findById(vegId);
		
		if(vegObjOpt.isEmpty()) throw new VegetableException("Invalid vegetable id");
		
		// if available in DB then getting object of that particular vegetable
		Vegetable vegetable = vegObjOpt.get(); 
		
		// fetching vegetable list from cart
		List<VegetableDTO> customerCartVegList = customerCart.getVegetable();
		
		// step to check weather vegetable with this vegId is available in Cart or not
		
		Integer cartAvailableQty = 0;
		for (VegetableDTO veg : customerCartVegList) {
			if(veg.getVegId() == vegId) {
				cartAvailableQty = veg.getQuantity();
			}
		}
		
		if(cartAvailableQty == 0) throw new VegetableException("This vegetable not found in cart");
	
		
		if(cartAvailableQty >= quantityToDecrease) {
	
			for (VegetableDTO veg : customerCartVegList) {
				if(veg.getVegId() == vegId) {
					veg.setQuantity(veg.getQuantity() - quantityToDecrease);
				}
			}

			// now updated vegList is set to the customer cart
			customerCart.setVegetable(customerCartVegList);
			
			// update quantity in DB
			vegetable.setQuantity(vegetable.getQuantity() + quantityToDecrease);
			
			vegeRepo.save(vegetable);
		}else {
			throw new VegetableException("Only "+cartAvailableQty+" available in Cart");
		}
		
		return customerCart;
	}


	@Override
	public Cart removeVegetable(Integer cartId, Integer vegId) throws VegetableException, CartException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Cart removeAllVegetable(Integer cartId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<VegetableDTO> viewAllItems(Integer customerId) throws CartException {
		
		Cart customerCart= CartRepo.findByCustomerId(customerId);
		
		if(customerCart == null) throw new CustomerException("Invalid Customer id");
		
		List<VegetableDTO> list = customerCart.getVegetable();
		
		return list;
	}
	
	
	
//
//	@Override
//	public Cart increaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException, CartException {
//		Optional<Cart> cart = CartRepo.findById(cartId);
//		Cart newCart= cart.get();
//		
//		if(cart.isPresent()) {
//			List<Vegetable> lists= cart.get().getVegetable();
//			
//			Optional<Vegetable> vege= vegeRepo.findById(vegId);
//			
//			
//			// Checking Vegetable Stock
//			if(!vege.isPresent()) {
//				throw new VegetableException("Vegetable is not in Stock");
//			}
//			
//			boolean flag= false;
//			
//			// If Required Stock is there Then else Throw error 
//			if(vege.get().getQuantity()>quantity) {
//				vege.get().setQuantity(vege.get().getQuantity()-quantity);
//				flag=true;
//			}else {
//				throw new VegetableException("Vegetable Quantity is not Available ");
//			}
//			
//			//If we have deducted the Stock quantity from the VegetableDTO
//			if(flag) {
//				for (Vegetable list : lists) {
//					if(list.getVegId() == vegId) {
//						list.setQuantity(list.getQuantity()+quantity);
//					}
//				}
//			}
//		
//			
//			newCart.setVegetable(lists);
//			
//			//Saving here 
//			Cart finalCart=CartRepo.save(newCart);
//			
//			return finalCart;
//		}else {
//			throw new CartException("Cart not found with id : "+ cartId);
//		}
//	}
//
//	@Override
//	public Cart decreaseVegQantity(Integer cartId, Integer quantity,Integer vegId) throws VegetableException, CartException {
//		Optional<Cart> cart = CartRepo.findById(cartId);
//		Cart newCart= cart.get();
//		
//		if(cart.isPresent()) {
//			List<Vegetable> lists= cart.get().getVegetable();
//			
//			for (Vegetable list : lists) {
//				if(list.getVegId() == vegId) {
//					list.setQuantity(list.getQuantity()-quantity);
//				}
//			}
//			
//			newCart.setVegetable(lists);
//			Cart finalCart=CartRepo.save(newCart);
//			
//			return finalCart;
//		}else {
//			throw new CartException("Cart not found with id : "+ cartId);
//		}
//	}
//
//	@Override
//	public Cart removeVegetable(Integer cartId, Integer vegId) throws VegetableException, CartException {
//		Optional<Cart> cart = CartRepo.findById(cartId);
//		Cart newCart= cart.get();
//		
//		if(cart.isPresent()) {
//			List<Vegetable> lists= cart.get().getVegetable();
//			
//			Vegetable veg= null;
//			
//			for (Vegetable list : lists) {
//				if(list.getVegId() == vegId) {
//					veg=list;
//					lists.remove(veg);
//					break;
//				}
//			}
//			
//			newCart.setVegetable(lists);
//			Cart finalCart=CartRepo.save(newCart);
//			
//			return finalCart;
//		}else {
//			throw new CartException("Cart not found with id : "+ cartId);
//		}
//	}
//
//	@Override
//	public Cart removeAllVegetable(Integer cartId) throws CartException {
//		Optional<Cart> cart = CartRepo.findById(cartId);
//		if(cart.isPresent()) {
//			
//			cart.get().setVegetable(null);
//			Cart newCart = CartRepo.save(cart.get());
//			return newCart;
//		}else {
//			throw new CartException("Cart not found with id : "+ cartId);
//		}
//		
//	}
//
//	@Override
//	public List<Vegetable> viewAllItems(Integer cartId) throws CartException {
//		Optional<Cart> cart1= CartRepo.findById(cartId);
//		List<Vegetable> list = null;
//		if(cart1.isPresent()) {
//			list =  cart1.get().getVegetable();
//		}else {
//			throw new CartException("Cart not found with id : "+ cartId);
//		}
//		return list;
//	}


	


}
