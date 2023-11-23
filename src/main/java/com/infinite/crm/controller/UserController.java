package com.infinite.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.crm.exception.UserNotFoundException;
import com.infinite.crm.model.LoginFront;
import com.infinite.crm.model.LoginMessage;
import com.infinite.crm.model.User;
import com.infinite.crm.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/n1")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		System.out.println("1");
		return userRepository.save(newUser);
	}

	@PostMapping(path = "/users/login")
	LoginMessage loginuser(@RequestBody LoginFront loginFront) {
		User email = userRepository.findByEmail(loginFront.getEmail());
		if (email != null) {
			String password = loginFront.getPassword();
			String userpass = email.getPassword();
			if (password.matches(userpass)) {
				return new LoginMessage("Login Success", true);
			} else {
				return new LoginMessage("Incorrect emailId or Password", false);
			}
		} else {
			return new LoginMessage("emailId not exist", false);
		}
	}

	@GetMapping("/users")
	List<User> getAllUsers() {
		return userRepository.findAll();
		
	}

	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id).map(user -> {
			user.setName(newUser.getName());
			user.setPassword(newUser.getPassword());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/api/n1/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id " + id + " has been deleted success.";
	}

}
