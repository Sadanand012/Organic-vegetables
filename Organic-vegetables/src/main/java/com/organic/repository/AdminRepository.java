package com.organic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organic.model.Admin;
@Controller
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	
	
//	@Query(value="Select * from admin", nativeQuery = true)
//	public List<Admin> getAllAdmins();
	
	public Admin findByEmailId(String email);
	public Admin findByEmailIdAndPassword(String emailId,String password);
}
