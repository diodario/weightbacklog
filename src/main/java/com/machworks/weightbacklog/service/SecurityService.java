package com.machworks.weightbacklog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.machworks.weightbacklog.dao.UserRepository;
import com.machworks.weightbacklog.dto.UserDto;
import com.machworks.weightbacklog.entity.User;

@Component
public class SecurityService implements SecurityServiceSpec{
	@Autowired
	private UserRepository userRepo;

	public boolean isValidUser(UserDto user) {
		return !userRepo.exists(user.getId());
	}
	
	private Long generateUserId(){
		return (long)Math.random()*100000;
	}
	
	public String registerUser(UserDto user) {
		if(isValidUser(user)){
			return "this user is already registered";
		}
		User userForSave = new User(user.getId(),user.getUserName(),user.getPassword());
		userRepo.save(userForSave);
		return "saved!";
	}

}
