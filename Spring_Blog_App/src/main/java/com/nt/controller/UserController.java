package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.dto.UserLogin;
import com.nt.dto.UserRegister;
import com.nt.model.PostInfo;
import com.nt.service.PostService;
import com.nt.service.UserService;

import jakarta.servlet.http.HttpSession;




@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String indexPage(Map<String, Object> map) {
		List<PostInfo> listOfPosts = postService.findAll();
		map.put("listOfPosts", listOfPosts);
		return "index";
	}
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("register", new UserRegister());
		return "register";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute UserRegister register,
			RedirectAttributes attrs) {
		String result = userService.registerUser(register);
		attrs.addFlashAttribute("msg", result);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("login", new UserLogin());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserLogin login,
			Model model) {
		String result = userService.loginUser(login);
		model.addAttribute("msg", result);
		if(result.contains("sucess")) {
			List<PostInfo> listOfPosts = postService.findAllPostsByUser();
			model.addAttribute("listOfPosts", listOfPosts);
			return "dashboard";
		}
		model.addAttribute("login", new UserLogin());
		return "login";
	}
	
	@GetMapping("/logout")
	public String logut( ) {
		session.invalidate();
		return "redirect:/";
	}
}
