package com.infinite.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.crm.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	public Admin findByEmail(String email);
	
	Admin findByPassword(String password);

	Admin findByEmailAndPassword(String email, String password);

}
