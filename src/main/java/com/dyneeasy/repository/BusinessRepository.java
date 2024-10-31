package com.dyneeasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dyneeasy.entity.AuthenticationEntity;
import com.dyneeasy.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
	
	Business getById(Long id);
	

	Optional<Business> findByEmail(String email);

}