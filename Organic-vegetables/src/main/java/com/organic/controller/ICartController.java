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
import com.organic.service.ICartService;

@RestController
public class ICartController {
	
	@Autowired
	private ICartService cartService;
	
	
	//add To Cart
	@PostMapping("/add/{id}")
	public ResponseEntity<Vegetable> addToCart(@RequestBody Vegetable veg,@PathVariable Integer id) throws VegetableException, CartException{
		
		Vegetable vegetable = cartService.addToCart(veg, id);
	
		return new ResponseEntity<Vegetable>(vegetable, HttpStatus.CREATED);
	}
	
	// Increase Quantity
	
	@PutMapping("/Increase/{cartId}/{vegId}/{quantity}")
	public ResponseEntity<Cart> increaseQuantity(@PathVariable("cartId")Integer cartId,@PathVariable("vegId")Integer vegId,@PathVariable("quantity")Integer quantity) throws VegetableException, CartException{
		Cart cart = cartService.increaseVegQantity(cartId, quantity, vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Decrease Quantity
	
	@PutMapping("/Increase/{cartId}/{vegId}/{quantity}")
	public ResponseEntity<Cart> decreaseQuantity(@PathVariable("cartId")Integer cartId,@PathVariable("vegId")Integer vegId,@PathVariable("quantity")Integer quantity) throws VegetableException, CartException{
		Cart cart = cartService.decreaseVegQantity(cartId, quantity, vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Delete Vegetable by id
	
	@DeleteMapping("/remove/{cartId}/{vegId}")
	public ResponseEntity<Cart> deleteVegetable(@PathVariable("cartId")Integer cartId,@PathVariable("vegId")Integer vegId) throws VegetableException, CartException{
		Cart cart = cartService.removeVegetable(cartId,vegId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	//Delete All Vegetable by id
	
	@GetMapping("remove/{id}")
	public ResponseEntity<Cart> removeAllvege(@PathVariable("id")Integer cartId) throws CartException{
		Cart cart = cartService.removeAllVegetable(cartId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	
	//view All vegetables
	@GetMapping("viewAll/{id}")
	public ResponseEntity<List<Vegetable>> listOfAllVegetableHandller(@PathVariable("id")Integer cartId) throws CartException{

		List<Vegetable> lists = cartService.viewAllItems(cartId);
		return new ResponseEntity<>(lists,HttpStatus.ACCEPTED);
	}
}
