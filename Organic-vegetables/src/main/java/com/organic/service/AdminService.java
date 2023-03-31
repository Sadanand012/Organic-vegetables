package com.organic.service;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.model.Admin;

import java.util.List;

public interface AdminService {
	
	public Admin addAdmin(Admin admin)throws AdminAlreadyExistException;
	public Admin updateAdmin(Admin admin)throws NoAdminFoundException;
	public Admin removeAdmin(Integer adminId)throws NoAdminFoundException;
	public Admin viewAdmin(Integer adminId)throws AdminIdNotFoundException;
	
	//Extra 
	public List<Admin> viewAllAdmin();
}
