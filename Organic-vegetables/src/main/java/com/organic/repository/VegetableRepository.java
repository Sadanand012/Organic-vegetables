package com.organic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organic.model.Vegetable;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Integer>{
	
	public List<Vegetable> findByName(String Name);
	
	public List<Vegetable> findByType(String Name);
	
}
