package com.dyneeasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dyneeasy.entity.AuthenticationEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Long>{
	Optional<AuthenticationEntity> findByEmail(String email); // Custom query method
}
