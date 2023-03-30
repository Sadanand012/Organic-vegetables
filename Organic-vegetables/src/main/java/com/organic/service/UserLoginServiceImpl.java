package com.organic.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.ThresholdingOutputStream;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.organic.exception.UserException;
import com.organic.model.Admin;
import com.organic.model.CurrentUserSession;
import com.organic.model.Customer;
import com.organic.model.User;
import com.organic.repository.AdminRepository;
import com.organic.repository.UserSessionRepo;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	//customer and admin repo
	
	
	
	@Autowired
	private AdminRepository adminDao;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	

	@Override
	public String logIn(User user) throws UserException {
		
		if(user.getRole().equalsIgnoreCase("admin")){
			 Admin admin = adminDao.findByEmailId(user.getUserId());
			 
			 if(admin==null) {
				 throw new UserException("Invalid User Id !");
			 }
			 Optional<CurrentUserSession> validOpt = userSessionRepo.findById(admin.getAdminId());
			 
			 if(validOpt.isPresent()) {
				 throw new UserException("User Already Logged In !");
			 }
			 
			 if(admin.getPassword().equals(user.getPassword())) {
				 String key= RandomStringUtils.random(6);
				 CurrentUserSession currentAdminSession = new CurrentUserSession(admin.getAdminId(),key,LocalDateTime.now());

				 userSessionRepo.save(currentAdminSession);

		         return currentAdminSession.toString();
			 }else {
				 throw new UserException("Please Enter valid Password");
			 }	 
			 
		}else if(user.getRole().equalsIgnoreCase("customer")){
			
			Customer customer = customerDao.findByEmailId(user.getUserId());
			 
			 if(customer==null) {
				 throw new UserException("Invalid User Id !");
			 }
			 Optional<CurrentUserSession> validOpt = userSessionRepo.findById(customer.getCustomerId());
			 
			 if(validOpt.isPresent()) {
				 throw new UserException("User Already Logged In !");
			 }
			 
			 if(customer.getPassword().equals(user.getPassword())) {
				 String key= RandomStringUtils.random(6);
				 
				 CurrentUserSession currentCustomerSession = new CurrentUserSession(customer.getCustomerId(),key,LocalDateTime.now());

				 userSessionRepo.save(currentCustomerSession);

		         return currentCustomerSession.toString();
			 }else {
				 throw new UserException("Please Enter valid Password");
			 }	 
			
		}else {
			throw new UserException("Invalid User Details !");
		}
		
	}

	@Override
	public String logOut(String key) throws UserException {
		
		CurrentUserSession currentUserSession = userSessionRepo.findByUuid(key);
		
		if(currentUserSession.equals(null)) {
			throw new UserException("User not logged In");
		}
		
		userSessionRepo.delete(currentUserSession);
		
		
		return "User Successfully Logged Out";
	}

}
