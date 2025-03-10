package com.wipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dto.ResponseTemplateVO;
import com.wipro.model.User;
import com.wipro.repository.UserRepository;
import com.wipro.service.EmailClient;
import com.wipro.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

   
    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

   
    @GetMapping("/{id}/withemail")
    public ResponseTemplateVO getUserWithEmail(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithEmail of UserController");
        return userService.getUserWithEmail(userId);
    }
}