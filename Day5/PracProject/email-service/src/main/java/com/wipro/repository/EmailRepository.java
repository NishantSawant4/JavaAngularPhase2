package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByEmail(String email); 
}