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
import com.organic.model.Admin;
import com.organic.service.IAdminService;

@RestController
public class IAdminController {
	
	@Autowired
	private IAdminService adminServiceDao;
	
	//Register Admin
	
	@PostMapping(path="/RegisterAdmin")
	public ResponseEntity<Admin> registerNewAdminHandler(@RequestBody Admin admin) throws AdminAlreadyExistException{
		
		Admin addAdmin= adminServiceDao.addAdmin(admin);
		
		return new ResponseEntity<Admin> (addAdmin,HttpStatus.CREATED);
		
	}
	
	
	//Update Admin
	@PutMapping(path="/updateAdmin/{adminId}")
	public ResponseEntity<Admin> updateAdm(@RequestBody Admin admin, @PathVariable Integer adminId) throws NoAdminFoundException{
		Admin updateAdm= adminServiceDao.updateAdmin(admin);
		
		return new ResponseEntity<Admin> (updateAdm,HttpStatus.OK);
	}
	
	
	//Remove Admin
	@PutMapping(path="/removeAdmin/{adminId}")
	public ResponseEntity<Admin> removeAdm(@PathVariable Integer adminId) throws NoAdminFoundException{
		
		Admin removeAdm = adminServiceDao.removeAdmin(adminId);
		
		return new ResponseEntity<Admin>(removeAdm,HttpStatus.OK); 
	}
	
	//View Admin
	@GetMapping(path="/viewAdmin/{id}")
	public ResponseEntity<Admin> viewAdm(@PathVariable("id") Integer adminId) throws NoAdminFoundException, AdminIdNotFoundException{
		
		Admin removeAdm = adminServiceDao.viewAdmin(adminId);
		
		return new ResponseEntity<Admin>(removeAdm,HttpStatus.OK); 
	}
	
	//View All 
	@GetMapping(path="/viewAdmin")
	public ResponseEntity<List> viewAdm(){
		
		List<Admin> list= adminServiceDao.viewAllAdmin();
		
		return new ResponseEntity<List>(list,HttpStatus.OK); 
	}
	
}
