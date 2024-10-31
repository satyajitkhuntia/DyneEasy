package com.dyneeasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dyneeasy.entity.User;
import com.dyneeasy.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/profile")
    public String showUserProfile(HttpSession session, Model model) {
        Object loggedInUser = session.getAttribute("user");
        if (loggedInUser instanceof User) { 
            model.addAttribute("user", (User)loggedInUser);
            
            return "user/profile";
        }
        return "redirect:/login";
    }
	
	@PostMapping("/user/profile")
	public String updateUserProfile(@ModelAttribute("user") User user, HttpSession session, Model model) {
	    try {
	        userService.updateUser(user);  // This will update only specified fields
	        
	        session.setAttribute("user", userService.getUser(user.getUserId()));  // Refresh session with updated user data
	        return "redirect:/user/profile";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        return "user/profile"; // Reload profile page with error message
	    }
	}

	
	
	
	@GetMapping("/user/home")
    public String showUserDashboard(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        if (user instanceof User) { 
            return "user/home";
        }
        return "redirect:/login";
    }
	@PostMapping("/user/home")
    public String processUserDashboard(HttpSession session, Model model) {
        Object loggedInUser = session.getAttribute("loggedInEntity");
        if (loggedInUser instanceof User) { 
           
            return "user/home";
        }
        return "redirect:/login";
    }
}
