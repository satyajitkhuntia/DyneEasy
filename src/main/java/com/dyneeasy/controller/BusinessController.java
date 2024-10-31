package com.dyneeasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dyneeasy.entity.Business;
import com.dyneeasy.service.BusinessService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	
	@GetMapping("/business/profile")
    public String showBusinessProfile(HttpSession session, Model model) {
        Object loggedInBusiness = session.getAttribute("business");
        if (loggedInBusiness instanceof Business) {
            Business business = (Business) loggedInBusiness;
            model.addAttribute("business", business); // Add business object to model
            return "business/profile"; // Return the profile view
        }
        return "redirect:/login"; // Redirect to login if not logged in
    }


	@PostMapping("/business/profile")
    public String updateBusinessProfile(@ModelAttribute("business") Business business, HttpSession session, Model model) {
        try {
            businessService.updateBusiness(business);  // Update only specified fields
            session.setAttribute("business", businessService.getBusiness(business.getId()));  // Refresh session with updated business data
            return "redirect:/business/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
            return "business/profile"; // Reload profile page with error message
        }
    }
	
	@GetMapping("/business/home")
    public String showClientDashboard(HttpSession session, Model model) {
        Object business = session.getAttribute("business");
        if (business instanceof Business) { 
            model.addAttribute("business", business); 
            return "business/home";
        }
        return "redirect:/login";
    }
	@PostMapping("/business/home")
    public String processClientDashboard(HttpSession session, Model model) {
        Object business = session.getAttribute("business");
        if (business instanceof Business) { 
            model.addAttribute("business", business); 
            return "business/home";
        }
        return "redirect:/login";
    }
}
