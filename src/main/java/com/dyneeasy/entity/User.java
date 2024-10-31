package com.dyneeasy.entity;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;  // Primary key

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = true)  // Chosen business (can be null initially)
    private Business business;

    @Column(nullable = true)
    private String password;  // Client's password (can be set later)

    @Column(nullable = true)
    private String name;  // Client's name

    @Column(nullable = false, unique = true)
    private String mobile;  // Client's mobile number

    @Column(nullable = false, unique = true)
    private String email;  // Client's email

    @Column(nullable = true)  // Client's delivery location (can be set later)
    private String location;

    @Column(nullable = true)
    private String gender;  // Client's gender (optional)

    private Double balance = 0.0;  // Client's balance (-ve for due, +ve for extra paid)

    private LocalDate joiningDate = LocalDate.now();  // Date when the client joined

    private boolean profileCompleted = false;  // Tracks if the profile is fully completed
    
}
