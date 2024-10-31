package com.dyneeasy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dyneeasy.binding.AuthenticationForm;
import com.dyneeasy.entity.AuthenticationEntity;
import com.dyneeasy.entity.Business;
import com.dyneeasy.entity.User;
import com.dyneeasy.service.AuthenticationService;
import com.dyneeasy.service.BusinessService;
import com.dyneeasy.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService service;
    
    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("client", new AuthenticationForm());
        return "authentication/signup";
    }

    @PostMapping("/signup")
    public String processSignup(AuthenticationForm form, Model model, HttpSession session) {
        if (form.getPassword().equals(form.getConfirmPassword())) {
            AuthenticationEntity entity = new AuthenticationEntity();
            entity.setMobile(form.getMobile());
            entity.setEmail(form.getEmail());
            entity.setPassword(form.getPassword());
            entity.setBusiness("business".equals(form.getUserType()));

            boolean saved = service.saveClient(entity);
            if (saved) {
                String otp = "123456";  // Replace with OTP generation logic
                entity.setOtp(otp);
                session.setAttribute("authEntity", entity);
                return "authentication/verify_otp";
            } else {
                model.addAttribute("msg", "Error occurred while saving. Please try again.");
                return "authentication/signup";
            }
        } else {
            model.addAttribute("client", form);
            model.addAttribute("msg", "Password and Confirm password must be the same!");
            return "authentication/signup";
        }
    }

    @GetMapping("/verify")
    public String showVerifyForm() {
        return "authentication/verify_otp";
    }

    @PostMapping("/verify")
    public String processVerifyOtp(@RequestParam("otp") String otpInput, Model model, HttpSession session) {
        AuthenticationEntity authEntity = (AuthenticationEntity) session.getAttribute("authEntity");
        
        if (authEntity != null && otpInput.equals(authEntity.getOtp())) {
            if (authEntity.isBusiness()) {
                Business business = new Business();
                business.setEmail(authEntity.getEmail());
                business.setMobile(authEntity.getMobile());
                business.setPassword(authEntity.getPassword());
                businessService.saveBusiness(business);
            } else {
                User client = new User();
                client.setEmail(authEntity.getEmail());
                client.setMobile(authEntity.getMobile());
                client.setPassword(authEntity.getPassword());
                userService.saveUser(client);
            }
            model.addAttribute("msg", "OTP verified successfully!");
            return "redirect:/login";
        } else {
            model.addAttribute("msg", "Invalid OTP, please try again.");
            return "authentication/verify_otp";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("entity", new AuthenticationForm());
        return "authentication/login";
    }

    @PostMapping("/login")
    public String processLogin(AuthenticationForm form, Model model, HttpSession session) {
        AuthenticationEntity authEntity = service.getAuthenticationEntityByEmail(form.getEmail());
        if (authEntity != null && authEntity.getPassword().equals(form.getPassword())) {
            if (authEntity.isBusiness()) {
                Optional<Business> businessOptional = businessService.getBusinessByEmail(authEntity.getEmail());
                if (businessOptional.isPresent()) {
                    session.setAttribute("business", businessOptional.get());
                    return "redirect:/business/home";
                } else {
                    model.addAttribute("msg", "Business account not found.");
                    return "authentication/login";
                }
            } else {
                Optional<User> userOptional = userService.getUserByEmail(authEntity.getEmail());
                if (userOptional.isPresent()) {
                    session.setAttribute("user", userOptional.get());
                    return "redirect:/user/home";
                } else {
                    model.addAttribute("msg", "User account not found.");
                    return "authentication/login";
                }
            }
        } else {
            model.addAttribute("msg", "Invalid credentials, please try again.");
            model.addAttribute("entity", new AuthenticationForm());
            return "authentication/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the current session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the current session
        }
        
        // Create a new session
        request.getSession(true); // This creates a new session
        
        return "redirect:/login"; // Redirect to the login page
    }
}
