package com.organic.service;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.exception.UserException;
import com.organic.model.Admin;

import java.util.List;

public interface AdminService {
	
	public Admin addAdmin(Admin admin)throws AdminAlreadyExistException;
	public Admin updateAdmin(Admin admin,String key)throws NoAdminFoundException, UserException;
	public Admin removeAdmin(Integer adminId,String key)throws NoAdminFoundException,UserException;
	public Admin viewAdmin(Integer adminId,String key)throws AdminIdNotFoundException,UserException;
	
	//Extra 
	public List<Admin> viewAllAdmin();
}
