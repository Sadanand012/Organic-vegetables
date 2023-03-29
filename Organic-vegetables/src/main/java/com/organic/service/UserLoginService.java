package com.organic.service;

import com.organic.exception.UserException;
import com.organic.model.User;

public interface UserLoginService {
	
	public String logIn(User user) throws UserException;

    public String logOut(String key) throws UserException;

}
