package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.AdminAlreadyExistException;
import com.organic.exception.AdminIdNotFoundException;
import com.organic.exception.NoAdminFoundException;
import com.organic.exception.UserException;
import com.organic.model.Admin;
import com.organic.model.CurrentUserSession;
import com.organic.repository.AdminRepository;
import com.organic.repository.UserSessionRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository repo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;

	//Add 
	@Override
	public Admin addAdmin(Admin admin,String key) throws AdminAlreadyExistException, AdminIdNotFoundException{
		
		
		if (!key.equals("organic")) {
			throw new AdminIdNotFoundException("Please Provide Valid key !...");
		}
		
		Admin adm= repo.findByEmailId(admin.getEmailId());
		
		
		if(adm != null) {
			throw new AdminAlreadyExistException("Admin already exist ");
		}
		
		repo.save(admin);
		
		return admin;
		
	}

	//Update
	
	@Override
	public Admin updateAdmin(Admin admin,String key) throws NoAdminFoundException, UserException {
		
		CurrentUserSession currentUserSession = userSessionRepo.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		Admin updAdm= repo.findById(admin.getAdminId())
				.orElseThrow(() -> new NoAdminFoundException("Admin not Exist with id : "+admin.getAdminId()));
		
		repo.save(admin);
		
		return admin;
		
	}

	//Remove
	
	@Override
	public Admin removeAdmin(Integer adminId,String key) throws NoAdminFoundException, UserException {
		
		CurrentUserSession currentUserSession = userSessionRepo.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
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
	public Admin viewAdmin(Integer adminId,String key) throws AdminIdNotFoundException, UserException {
		CurrentUserSession currentUserSession = userSessionRepo.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("Invalid session Id or Admin not Logged In");
		}
		
		
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
		
		return repo.findAll();
	}


}
