package com.organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.FeedbackException;
import com.organic.model.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackService feedbackService;

	@Override
	public Feedback addFeedback(Feedback feedback) throws FeedbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> viewAllFeedbacks(Integer vegId) throws FeedbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> viewAllFeedbacksOfParticularCustomer(Integer customerId) throws FeedbackException {
		// TODO Auto-generated method stub
		return null;
	}

}
