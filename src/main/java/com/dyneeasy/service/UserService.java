package com.dyneeasy.service;

import com.dyneeasy.entity.User;
import com.dyneeasy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    /**
     * Fetch a client by their unique client ID.
     *
     * @param clientId the ID of the client
     * @return the User entity if found
     * @throws RuntimeException if the client is not found
     */
    public User getUser(Long userId) {
        return repo.findById(userId)
                   .orElseThrow(() -> new RuntimeException("Client not found with ID: " + userId));
    }

    /**
     * Update an existing client.
     *
     * @param client the User entity to update
     */
    public void updateUser(User updatedUserData) {
        // Fetch the existing user from the database using client ID
        User existingUser = repo.findById(updatedUserData.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + updatedUserData.getUserId()));

        // Update only the fields that are allowed to change
        existingUser.setName(updatedUserData.getName());
        existingUser.setGender(updatedUserData.getGender());
        existingUser.setEmail(updatedUserData.getEmail());
        existingUser.setMobile(updatedUserData.getMobile());
        existingUser.setLocation(updatedUserData.getLocation());

        // Save the updated user entity
        repo.save(existingUser);
    }

    /**
     * Save a new client.
     *
     * @param client the User entity to save
     */
    public void saveUser(User user) {
        repo.save(user);
    }

    /**
     * Fetch a client by their email address.
     *
     * @param email the email of the client
     * @return an Optional containing the User entity if found, otherwise empty
     */
    public Optional<User> getUserByEmail(String email) {
        return repo.findByEmail(email);
    }
    
}
