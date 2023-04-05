package com.organic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.organic.exception.UserException;
import com.organic.model.User;
import com.organic.service.UserLoginServiceImpl;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserLoginServiceImpl userService;
	
	@PostMapping("/login")
    public ResponseEntity<String> logInUserHandler(@Valid @RequestBody User user) throws UserException {

        String key = userService.logIn(user);

        return new ResponseEntity<String>(key, HttpStatus.OK);

    }
	
	@PostMapping("/logout")
    public String logoutUserHandler(@RequestParam(required = false) String key) throws UserException {
        
		return userService.logOut(key);

    }

}
