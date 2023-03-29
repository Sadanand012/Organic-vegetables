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

import com.organic.exception.VegetableException;
import com.organic.model.Vegetable;
import com.organic.service.VegetableServiceImpl;

@RestController
public class VegetableController {

	
	@Autowired
	private VegetableServiceImpl vs;
	
	
	@PostMapping("/addVegetable")
	public ResponseEntity<Vegetable> addVegetableH(@RequestBody Vegetable vegetable){
//		System.out.println(vegetable);
		Vegetable veg=vs.addVegetable(vegetable);
		return new ResponseEntity<>(veg,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getListvegetables")
	public ResponseEntity<List<Vegetable>> allVegetableListH() throws VegetableException{
		
		List<Vegetable> list =vs.viewAllVegetables();
		return new ResponseEntity<List<Vegetable>>(list,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/deleteVegetable/{vegid}")
	public ResponseEntity<Vegetable> deleteVegetableByIDH(@PathVariable("vegid") Integer vegId) throws VegetableException{
		
		Vegetable veg =vs.removeVegetable(vegId);
		return new ResponseEntity<Vegetable>(veg,HttpStatus.OK);
		
	}
	
	@PutMapping("/updateVegetable/{vegid}")
	public ResponseEntity<Vegetable> updateVegetableH(@RequestBody Vegetable vegetable) throws VegetableException{
		
		Vegetable updated =vs.updateVegetable(vegetable);
		return new ResponseEntity<Vegetable>(updated,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getVegetableByName/{vegname}")
	public ResponseEntity<List<Vegetable>> getVegetableListByNameH(@PathVariable("vegname") String name) throws VegetableException{
		
		List<Vegetable> list =vs.getVegetableByName(name);
		
		return new ResponseEntity<List<Vegetable>>(list,HttpStatus.OK);
		
	}

	@GetMapping("/getVegetableByCategory/{vegtype}")
	public ResponseEntity<List<Vegetable>> getVegetableListByVegTypeH(@PathVariable("vegtype") String type) throws VegetableException{
		
		List<Vegetable> list =vs.viewVegetableList(type);
		
		return new ResponseEntity<List<Vegetable>>(list,HttpStatus.OK);
		
	}
	
}
