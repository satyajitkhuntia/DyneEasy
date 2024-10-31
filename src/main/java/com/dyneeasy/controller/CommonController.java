package com.dyneeasy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dyneeasy.entity.Business;
import com.dyneeasy.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommonController {
	
	@GetMapping("/")
	public String parentRequestHandler(Model model) {
		return "/common/home";
	}
	
	@GetMapping("/home")
	public String showDashboard(HttpSession session, Model model) {
	    Object loggedInUser = session.getAttribute("loggedInEntity");

	    if (loggedInUser instanceof Business) {
	        return "business/home";
	    } else if (loggedInUser instanceof User) {
	        return "user/home";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/profile")
    public String showClientProfile(HttpSession session, Model model) {
        Object loggedInUser = session.getAttribute("loggedInEntity");
        if (loggedInUser instanceof User) { 
            return "user/profile";
        } else if (loggedInUser instanceof Business) { 
            return "business/profile";
        }
        return "redirect:/login";
    }
	@GetMapping("/xxx")
	public String demo(Model model) {
		model.addAttribute("entity", new User());
		return "user/profile";
	}
}
