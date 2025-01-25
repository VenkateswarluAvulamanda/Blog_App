package com.nt.service;

import com.nt.dto.UserLogin;
import com.nt.dto.UserRegister;

public interface UserService {
	
	public String registerUser(UserRegister register);
	
	public String loginUser(UserLogin login);
}
