package com.organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.exception.UserException;
import com.organic.model.Admin;
import com.organic.service.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminServiceDao;
	
	//Register Admin
	
	@PostMapping(path="/registerAdmin/{authorization_key}")
	public ResponseEntity<Admin> registerNewAdminHandler(@Valid @RequestBody Admin admin,@PathVariable String authorization_key) throws AdminAlreadyExistException, AdminIdNotFoundException{
		
		Admin addAdmin= adminServiceDao.addAdmin(admin,authorization_key);
		
		return new ResponseEntity<Admin> (addAdmin,HttpStatus.CREATED);
		
	}
	
	
	//Update Admin
	@PutMapping(path="/updateAdmin/{key}")
	public ResponseEntity<Admin> updateAdm(@Valid @RequestBody Admin admin,@PathVariable("key") String key) throws NoAdminFoundException, UserException{
		Admin updateAdm= adminServiceDao.updateAdmin(admin,key);
		
		return new ResponseEntity<Admin> (updateAdm,HttpStatus.OK);
	}
	
	
	//Remove Admin
	@PutMapping(path="/removeAdmin/{adminId}/{key}")
	public ResponseEntity<Admin> removeAdm(@PathVariable Integer adminId,@PathVariable("key") String key) throws NoAdminFoundException, UserException{
		
		Admin removeAdm = adminServiceDao.removeAdmin(adminId,key);
		
		return new ResponseEntity<Admin>(removeAdm,HttpStatus.OK); 
	}
	
	//View Admin
	@GetMapping(path="/viewAdmin/{id}/{key}")
	public ResponseEntity<Admin> viewAdm(@PathVariable("id") Integer adminId,@PathVariable("key") String key) throws NoAdminFoundException, AdminIdNotFoundException, UserException{
		
		Admin removeAdm = adminServiceDao.viewAdmin(adminId,key);
		
		return new ResponseEntity<Admin>(removeAdm,HttpStatus.OK); 
	}
	
	//View All 
	@GetMapping(path="/viewAdmin")
	public ResponseEntity<List> viewAdm(){
		
		List<Admin> list= adminServiceDao.viewAllAdmin();
		
		return new ResponseEntity<List>(list,HttpStatus.OK); 
	}
	
}
