package com.example.RecruitmentService.controller;

import com.example.RecruitmentService.entity.User;
import com.example.RecruitmentService.service.UserService;
import com.example.RecruitmentService.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class RegistrationController {
	private UserService userService;
	private UserServiceImpl userServiceImpl;

	@Autowired
	public RegistrationController(UserService userService, UserServiceImpl userServiceImpl) {
		this.userService = userService;
		this.userServiceImpl=userServiceImpl;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute(new User());
		return "registration";
	}

	@GetMapping("/start")
	public String start(Principal principal, Model model){
		User user=userServiceImpl.getUserByUserName(principal);
		model.addAttribute("user", user);
		return "start";
	}

	@PostMapping("/registration")
	public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userService.save(user);
		return "start";
	}
}
