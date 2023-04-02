package com.organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organic.exception.CartException;
import com.organic.exception.VegetableException;
import com.organic.model.Cart;
import com.organic.model.Vegetable;
import com.organic.model.VegetableDTO;
import com.organic.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	//Create Cart
	@PostMapping("/Cart")
	public ResponseEntity<Cart> CreateCart(@RequestBody Cart cart) throws CartException{
		
		Cart c1 = cartService.createCart(cart);
	
		return new ResponseEntity<Cart>(c1, HttpStatus.CREATED);
	}
	
	//add To Cart
	@PostMapping("/add/{vegId}/{customerId}")
	public ResponseEntity<String> addToCart(@PathVariable Integer vegId,@PathVariable Integer customerId) throws VegetableException, CartException{
		
		String vegetable = cartService.addToCart(vegId, customerId);
	
		return new ResponseEntity<String>(vegetable, HttpStatus.CREATED);
	}
	
	// Increase Quantity
	
	@PutMapping("/Increase/{customerId}/{vegId}/{quantity}")
	public ResponseEntity<Cart> increaseQuantity(@PathVariable Integer customerId,@PathVariable("vegId")Integer vegId,@PathVariable("quantity")Integer quantity) throws VegetableException, CartException{
		Cart cart = cartService.increaseVegQantity(customerId, quantity, vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Decrease Quantity
	
	@PutMapping("/decrease/{customerId}/{vegId}/{quantity}")
	public ResponseEntity<Cart> decreaseQuantity(@PathVariable Integer customerId,@PathVariable("vegId")Integer vegId,@PathVariable("quantity")Integer quantity) throws VegetableException, CartException{
		Cart cart = cartService.decreaseVegQantity(customerId, quantity, vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Delete Vegetable by id
	
	@DeleteMapping("/remove/{cartId}/{vegId}")
	public ResponseEntity<Cart> deleteVegetable(@PathVariable("cartId")Integer cartId,@PathVariable("vegId")Integer vegId) throws VegetableException, CartException{
		Cart cart = cartService.removeVegetable(cartId,vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Delete All Vegetable by id
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Cart> removeAllvege(@PathVariable("id")Integer cartId) throws CartException{
		Cart cart = cartService.removeAllVegetable(cartId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	
	//view All vegetables
	@GetMapping("viewAllVegetableList/{customerId}")
	public ResponseEntity<List<VegetableDTO>> listOfAllVegetableHandller(@PathVariable Integer customerId) throws CartException{

		List<VegetableDTO> lists = cartService.viewAllItems(customerId);
		return new ResponseEntity<>(lists,HttpStatus.ACCEPTED);
	}
}
