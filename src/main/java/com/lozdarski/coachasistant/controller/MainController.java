package com.lozdarski.coachasistant.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lozdarski.coachasistant.entity.RolesEntity;
import com.lozdarski.coachasistant.entity.UserEntity;
import com.lozdarski.coachasistant.repository.RolesRepository;
import com.lozdarski.coachasistant.repository.UserRepository;


@RequestMapping("/users")
@RestController
public class MainController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RolesRepository rolesRepository;

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public @ResponseBody String addNewUser (@RequestBody UserEntity user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println("USER");
		System.out.println("USER: " + user);
		System.out.println("USER");

		
		int userWithUserNameAndEmail = 0;
		
		userWithUserNameAndEmail += userRepository.findUsersByUsername(user.getUserName()).size();
		userWithUserNameAndEmail += userRepository.findUsersByEmail(user.getEmail()).size();
		
		if (userWithUserNameAndEmail == 0) {
			user.setPassword(encoder.encode(user.getPassword()));
			user.setIsBlocked(false);
			user.setIsEnabled(true);
			user.setDateCreated(new Date());
			userRepository.save(user);
			
			RolesEntity role = new RolesEntity();
			role.setRoleName("ROLE_USER");
			role.setUserIdFk(user);
			role.setUserNameFk(user.getUserName());
			rolesRepository.save(role);
			
			return "{'status' : 'success', 'message' : 'created new user'}";
		}
		
		
		return "{'status' : 'error', 'message' : 'error while user was creating'}";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}
}
