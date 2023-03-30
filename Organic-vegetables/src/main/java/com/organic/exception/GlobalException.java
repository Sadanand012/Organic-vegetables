package com.organic.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> MycommonexcHandler(Exception ie,WebRequest req){
		MyErrorDetails me= new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NoHandlerFoundException(NoHandlerFoundException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminAlreadyExistException.class)
	public ResponseEntity<MyErrorDetails> AdminAlreadyExistExceptionInfo(AdminAlreadyExistException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoAdminFoundException.class)
	public ResponseEntity<MyErrorDetails> NoAdminFoundExceptionInfo(NoAdminFoundException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminIdNotFoundException.class)
	public ResponseEntity<MyErrorDetails> NoAdminFoundExceptionInfo(AdminIdNotFoundException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> NoAdminFoundExceptionInfo(CartException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
}
