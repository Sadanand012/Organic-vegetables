package com.organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organic.exception.UserException;
import com.organic.model.Feedback;
import com.organic.service.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	
	@PostMapping("/feedbacks/{key}")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback, @PathVariable("key") String sessionKey) throws UserException{
		
		Feedback addedFeedback = feedbackService.addFeedback(feedback, sessionKey);
		
		return new ResponseEntity<>(addedFeedback, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/feedbacks/{vegId}")
	public ResponseEntity<List<Feedback>> viewAllFeedbacks(@PathVariable Integer vegId){
		
		List<Feedback> feedbacks = feedbackService.viewAllFeedbacks(vegId);
		
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/feedbacks/customers/{id}")
	public ResponseEntity<List<Feedback>> viewAllFeedbacksOfParticularCustomer(@PathVariable("id") Integer customerId){
		
		List<Feedback> feedbacks = feedbackService.viewAllFeedbacksOfParticularCustomer(customerId);
		
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
