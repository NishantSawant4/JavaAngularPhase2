package com.wipro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dto.ResponseTemplateVO;
import com.wipro.model.Email;
import com.wipro.model.User;
import com.wipro.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private static final String EMAIL_SERVICE = "emailService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailClient emailClientService;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    @CircuitBreaker(name = EMAIL_SERVICE, fallbackMethod = "emailServiceFallback")
    public ResponseTemplateVO getUserWithEmail(Long userId) {
        log.info("Inside getUserWithEmail of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        
        Email email = emailClientService.getEmailByUserId(user.getEmail());
        vo.setUser(user);
        vo.setEmail(email);
        return vo;
    }

    public ResponseTemplateVO emailServiceFallback(Long userId, Exception e) {
        log.error("Email Service is down, returning fallback response.", e);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).orElse(null);
        vo.setUser(user);
        vo.setEmail(new Email(0L, "no-email@example.com", "No Subject", "No Status"));
        return vo;
    }
}
