package com.spring.project.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("userMsg", "Invalid username and/or password");
		}
		
		if (logout != null) {
			model.addAttribute("logoutMsg", "You have been successfully logged out"); 
		}
		
		return "login";
	}

}
