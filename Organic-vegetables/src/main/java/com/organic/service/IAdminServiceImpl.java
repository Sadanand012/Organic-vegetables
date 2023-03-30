package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.model.Admin;
import com.organic.repository.AdminRepository;

@Service
public class IAdminServiceImpl implements IAdminService{
	
	private AdminRepository repo;

	//Add 
	@Override
	public Admin addAdmin(Admin admin) {
		
		return repo.save(admin);
		
	}

	//Update
	
	@Override
	public Admin updateAdmin(Admin admin) throws NoAdminFoundException {
		
		Admin updAdm= repo.findById(admin.getAdminId())
				.orElseThrow(() -> new NoAdminFoundException("Admin not Exist with id : "+admin.getAdminId()));
		
		return repo.save(updAdm);
		
	}

	//Remove
	
	@Override
	public Admin removeAdmin(Integer adminId) throws NoAdminFoundException {
		Optional<Admin> adm= repo.findById(adminId);
		if(repo.existsById(adminId)) {
			repo.delete(adm.get());
		}else {
			throw new NoAdminFoundException("Admin not Exist with id : "+adminId);
		}
		return adm.get();
	}

	//viewBy Id
	
	@Override
	public Admin viewAdmin(Integer adminId) throws AdminIdNotFoundException {
		Optional<Admin> adm= repo.findById(adminId);
		
		if(repo.existsById(adminId)) {
			return adm.get();
		}else {
			throw new AdminIdNotFoundException("Admin not Exist with id : "+adminId);
		}
		
	}

	//View All Admin
	
	@Override
	public List<Admin> viewAllAdmin() {
		
		return repo.getAllAdmins();
	}


}
