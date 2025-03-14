package com.wipro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.model.Email;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class EmailClient {

	 @Autowired
	    private RestTemplate restTemplate;

	    @CircuitBreaker(name = "emailService", fallbackMethod = "emailFallback")
	    public Email getEmailByUserId(String email) {
	        String url = "http://localhost:8082/email/get?email=" + email;
	        ResponseEntity<Email> response = restTemplate.getForEntity(url, Email.class);
	        return response.getBody();
	    }

	    public Email emailFallback(String userId, Throwable throwable) {
	        return new Email(0L, "no-email@example.com", "No Subject", "No Status");
	    }
	}