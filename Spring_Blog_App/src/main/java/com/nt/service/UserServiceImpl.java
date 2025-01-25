package com.nt.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.UserLogin;
import com.nt.dto.UserRegister;
import com.nt.model.UserInfo;
import com.nt.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String registerUser(UserRegister register) {
		Optional<UserInfo> info = userRepo.findByEmail(register.getEmail());
		if(info.isPresent()) {
			return "User exits with given mail id ";
		}
		UserInfo user = new UserInfo();
		BeanUtils.copyProperties(register, user);
		userRepo.save(user);
		return "User Registered Sucessfully";
	}
	
	@Override
	public String loginUser(UserLogin login) {
		Optional<UserInfo> info = userRepo.findByEmail(login.getEmail());
		if(info.isPresent()) {
			UserInfo user = info.get();
			if(user.getPazzword().equals(login.getPazzword())) {
				session.setAttribute("user", user);
				return "Login sucess";
			}
			return "Inavlid creditianls";
		}
		return "User deos not exits";
	}

}
