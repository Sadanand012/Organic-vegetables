package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.FeedbackException;
import com.organic.exception.OrderNotFoundException;
import com.organic.exception.UserException;
import com.organic.exception.VegetableException;
import com.organic.model.CurrentUserSession;
import com.organic.model.Feedback;
import com.organic.model.Order;
import com.organic.model.Vegetable;
import com.organic.repository.FeedbackRepository;
import com.organic.repository.OrderRepository;
import com.organic.repository.UserSessionRepo;
import com.organic.repository.VegetableRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	
	@Autowired
	private UserSessionRepo currentUserSession;
	
	@Autowired
	private VegetableRepository vegetableRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Feedback addFeedback(Feedback feedback, String sessionKey) throws FeedbackException, UserException {
		
		
		// 1st check this customer is logged in or not.
		CurrentUserSession loggedInUser = currentUserSession.findByUuid(sessionKey);
		
		if(loggedInUser == null) throw new FeedbackException("Please login first, to give feedback");
		
		// 2nd check if customerId from feedback is same as loggedInUser (means:- same user is adding feedback).
		if(loggedInUser.getUserId().equals(feedback.getCustomerId())) {
			
			//3rd check whether any vegetable is exist with same vegId (which is provided in the feedback) or not.
			Optional<Vegetable> existingVegetableOpt = vegetableRepository.findById(feedback.getVegId());
			
			if(existingVegetableOpt.isEmpty()) throw new VegetableException("No any vegetable found with this id");
			
			// 4th check whether this customer purchased this vegetable or not.
			List<Order> orders = orderRepository.findByCustomerId(feedback.getCustomerId());
			
			boolean isPurchased = orders.stream().anyMatch(o -> o.getVegetableList().stream().anyMatch(v -> v.getVegId() == feedback.getVegId()));
			
			if(!isPurchased) throw new OrderNotFoundException("Can't give feedback without purchase");
			
			return feedbackRepository.save(feedback);  // now feedback added
			
		}
		throw new UserException("Invalid user");
	}

	@Override
	public List<Feedback> viewAllFeedbacks(Integer vegId) throws FeedbackException {
		List<Feedback> feedbacks = feedbackRepository.findByVegId(vegId);
		
		if(feedbacks == null) throw new FeedbackException("No any feedback found");
		
		return feedbacks;
		
	}

	@Override
	public List<Feedback> viewAllFeedbacksOfParticularCustomer(Integer customerId) throws FeedbackException {
		List<Feedback> feedbacks = feedbackRepository.findByCustomerId(customerId);
		
		if(feedbacks == null) throw new FeedbackException("No any feedback found");
		
		return feedbacks;
	}

}
