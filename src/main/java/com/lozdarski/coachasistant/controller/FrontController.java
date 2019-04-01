package com.lozdarski.coachasistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view")
@Controller
public class FrontController {

	@RequestMapping("/")
	public String homePage() {
		
		return "index";
	}
	
	@RequestMapping("/main")
	public String mainView() {
		
		return "logged/main";
	}
}
