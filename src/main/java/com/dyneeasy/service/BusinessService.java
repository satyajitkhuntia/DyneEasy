package com.dyneeasy.service;

import com.dyneeasy.entity.Business;
import com.dyneeasy.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repo;

    /**
     * Fetch a Business by its unique ID.
     *
     * @param businessId the ID of the business
     * @return the Business entity if found
     * @throws RuntimeException if the business is not found
     */
    public Business getBusiness(Long businessId) {
        return repo.findById(businessId)
                   .orElseThrow(() -> new RuntimeException("Business not found with ID: " + businessId));
    }

    /**
     * Update an existing business.
     *
     * @param business the Business entity to update
     */
    public void updateBusiness(Business updatedBusinessData) {
        // Fetch the existing business from the database using its ID
        Business existingBusiness = repo.findById(updatedBusinessData.getId())
                .orElseThrow(() -> new RuntimeException("Business not found with ID: " + updatedBusinessData.getId()));

        // Update only the fields that are allowed to change
        existingBusiness.setBrand(updatedBusinessData.getBrand());
        existingBusiness.setMobile(updatedBusinessData.getMobile());
        existingBusiness.setEmail(updatedBusinessData.getEmail());
        existingBusiness.setTagline(updatedBusinessData.getTagline());

        // Save the updated business entity
        repo.save(existingBusiness);
    }

    /**
     * Save a new business to the repository.
     *
     * @param business the Business entity to save
     */
    public void saveBusiness(Business business) {
        repo.save(business);
    }

    /**
     * Fetch a business by its email.
     *
     * @param email the email of the business
     * @return an Optional containing the Business entity if found, otherwise empty
     */
    public Optional<Business> getBusinessByEmail(String email) {
        return repo.findByEmail(email);
    }
}
