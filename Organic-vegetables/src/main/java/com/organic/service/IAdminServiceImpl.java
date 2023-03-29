package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.model.Admin;
import com.organic.repository.IAdminRepository;

@Service
public class IAdminServiceImpl implements IAdminService{
	
	private IAdminRepository repo;

	@Override
	public Admin addAdmin(Admin admin) throws AdminAlreadyExistException {
		
		if(repo.existsById(admin.getAdminId())){
			throw new AdminAlreadyExistException("Admin Already Exist !");
		}else {
			return repo.save(admin);
		}
	}

	@Override
	public Admin updateAdmin(Admin admin) throws NoAdminFoundException {
		
		int result=0;
		
		
		int AdmId= admin.getAdminId();
		String cont_Num= admin.getContactNumber();
		String email= admin.getEmailId();
		
		result = repo.updateAdminById(cont_Num, email, AdmId);
		
		if(result>=1) {
			return admin;
		}else {
			throw new NoAdminFoundException("Admin not Exist with id : "+admin.getAdminId());
		}
	}

	@Override
	public Admin removeAdmin(int adminId) throws NoAdminFoundException {
		Optional<Admin> adm= repo.findById(adminId);
		if(repo.existsById(adminId)) {
			repo.delete(adm.get());
		}else {
			throw new NoAdminFoundException("Admin not Exist with id : "+adminId);
		}
		return adm.get();
	}

	@Override
	public Admin viewAdmin(Admin admin) throws AdminIdNotFoundException {
		
		if(repo.existsById(admin.getAdminId())) {
			return repo.getById(admin.getAdminId());
		}else {
			throw new AdminIdNotFoundException("Admin not Exist with id : "+admin.getAdminId());
		}
		
	}

	@Override
	public List<Admin> viewAllAdmin() {
		
		return repo.getAllAdmins();
	}

//	public Admin findById(int id) throws NoAdminFoundException{
//		return repo.findById(id).orElseThrow(()->new NoAdminFoundException("Admin not Exists !.."));
//	}
}
