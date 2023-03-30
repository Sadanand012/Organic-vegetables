package com.organic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organic.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
	
	public List<Feedback> findByVegId(Integer vegId);
	
	public List<Feedback> findByCustomerId(Integer customerId);

}
