package com.dyneeasy.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @Column(nullable = true, unique = true)
    private String brand;  // Business brand name

    @Column(nullable = false)
    private String password;  // Business login password

    private LocalDate joiningDate = LocalDate.now();  // Date when the business joined

    @Column(nullable = false, unique = true)
    private String mobile;  // Business contact mobile number

    @Column(nullable = false, unique = true)
    private String email;  // Business contact email address

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "business_locations", joinColumns = @JoinColumn(name = "business_id"))
    @Column(name = "location")
    private List<String> locations = new ArrayList<>();
   


    private String tagline;

    private Double rating;
}
