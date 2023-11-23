package com.infinite.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.crm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	  User findByEmail(String email);
	  
	  User findByPassword(String password);
	  
	  User findByEmailAndPassword(String email, String password);
	 
}
