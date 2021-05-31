 package com.ramninder.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramninder.springcloud.model.User;
import com.ramninder.springcloud.repos.UserRepo;
import com.ramninder.springcloud.security.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("/showReg")
	public String showRegisterPage() {
		
		return "registerUser";
		
	}
	
	@PostMapping("/registerUser")
	public String register(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login";
	}
	
	@GetMapping("/")
	public String showLoginPage() {
		
		return "login";
		
	}
	
	@PostMapping("/login")
	public String login(String email, String password) {
		boolean loginResponse = securityService.login(email, password);
		if(loginResponse) {
			return "index";
		}
		
		return "login";
		
	}

}
