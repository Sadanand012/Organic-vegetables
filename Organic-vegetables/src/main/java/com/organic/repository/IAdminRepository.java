package com.organic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{
	
	
	@Modifying
	@Query(value="Update admin set contactNumber=?1,emailId=?2,where adminId=?3",nativeQuery = true)
	public int updateAdminById(String contactNumber,String emailId,int adminId);
	
	@Query(value="Select * from admin", nativeQuery = true)
	public List<Admin> getAllAdmins();
	

}
