package com.wipro.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.model.Email;
import com.wipro.repository.EmailRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String email, @RequestParam String name) {
        String status = "SENT";
        emailRepository.save(new Email(null, email, "Welcome Email", status));
        return ResponseEntity.ok("Welcome email sent to " + email + " for " + name);
    }

    @GetMapping("/get")
    public ResponseEntity<Email> getEmail(@RequestParam String email) {
        List<Email> emails = emailRepository.findByEmail(email);
        if (emails != null && !emails.isEmpty()) {
            return ResponseEntity.ok(emails.get(0));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
