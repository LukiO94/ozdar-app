package com.lozdarski.coachasistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/logowanie")
	public String login() {
		
		return "securitytemplates/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		
		return "securitytemplates/register";
	}
}
