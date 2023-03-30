package com.organic.service;

import java.util.List;

import com.organic.exception.FeedbackException;
import com.organic.exception.UserException;
import com.organic.model.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback, String sessionKey) throws FeedbackException, UserException;
	
	
	public List<Feedback> viewAllFeedbacks(Integer vegId) throws FeedbackException;
	
	
	public List<Feedback> viewAllFeedbacksOfParticularCustomer(Integer customerId) throws FeedbackException;
	
}
