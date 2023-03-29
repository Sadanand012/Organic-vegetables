package com.organic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organic.exception.AdminAlreadyExistException;
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
	@PutMapping("path=/updateAdmin/{adminId}")
	public ResponseEntity<Admin> updateAdm(@RequestBody Admin admin, @PathVariable int adminId) throws NoAdminFoundException{
		Admin updateAdm= adminServiceDao.updateAdmin(admin);
		
		return new ResponseEntity<Admin> (updateAdm,HttpStatus.OK);
	}
	
	
	//Remove Admin
	@PutMapping("path=/removeAdmin/{adminId}")
	public ResponseEntity<Admin> removeAdm(@PathVariable int adminId) throws NoAdminFoundException{
		
		Admin removeAdm = adminServiceDao.removeAdmin(adminId);
		
		return new ResponseEntity<Admin>(removeAdm,HttpStatus.OK); 
	}
}
