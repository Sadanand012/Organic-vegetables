package com.organic.service;

import java.util.List;

import com.organic.exception.FeedbackException;
import com.organic.model.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback) throws FeedbackException;
	
	
	public List<Feedback> viewAllFeedbacks(Integer vegId) throws FeedbackException;
	
	
	public List<Feedback> viewAllFeedbacksOfParticularCustomer(Integer customerId) throws FeedbackException;
	
}
