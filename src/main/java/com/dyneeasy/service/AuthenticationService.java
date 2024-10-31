
package com.dyneeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyneeasy.entity.AuthenticationEntity;
import com.dyneeasy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository repo;

    // Save client or business
    public boolean saveClient(AuthenticationEntity entity) {
        try {
            repo.save(entity); // Assuming repo.save persists the entity
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find user by email
    public AuthenticationEntity getAuthenticationEntityByEmail(String email) {
        return repo.findByEmail(email).orElse(null); // Assuming findByEmail is defined in the repo
    }

    // Send OTP to email (this is a placeholder)
    public void sendOtpToEmail(String email, String otp) {
        System.out.println("Sending OTP: " + otp + " to email: " + email);
        // Actual email-sending logic here
    }
}

