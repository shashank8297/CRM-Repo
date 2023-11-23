package com.infinite.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.crm.model.User;
import com.infinite.crm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	  public User findByEmail(String email) {
		  return userRepository.findByEmail(email);
	  }
	  
	  public User findByPassword(String password) {
		  return userRepository.findByPassword(password);
	  }
	  
	  public User findByEmailAndPassword(String email, String password) {
		  return userRepository.findByEmailAndPassword(email, password);
	  }
	 
}
