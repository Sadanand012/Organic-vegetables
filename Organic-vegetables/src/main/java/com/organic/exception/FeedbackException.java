package com.organic.exception;

public class FeedbackException extends RuntimeException{

	public FeedbackException() {}
	
	public FeedbackException(String message) {
		super(message);
	}
}
