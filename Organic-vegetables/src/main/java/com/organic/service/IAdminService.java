package com.organic.service;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.model.Admin;

import java.util.List;

public interface IAdminService {
	
	public Admin addAdmin(Admin admin)throws AdminAlreadyExistException;
	public Admin updateAdmin(Admin admin)throws NoAdminFoundException;
	public Admin removeAdmin(int adminId)throws NoAdminFoundException;
	public Admin viewAdmin(Admin admin)throws AdminIdNotFoundException;
	
	//Extra 
	public List<Admin> viewAllAdmin();
}
