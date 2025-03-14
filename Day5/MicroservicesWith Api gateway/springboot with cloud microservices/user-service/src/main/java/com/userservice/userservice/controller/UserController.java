package com.userservice.userservice.controller;

import com.userservice.userservice.entity.User;
import com.userservice.userservice.service.UserService;
import com.userservice.userservice.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }

	/*
	 * @GetMapping("/call-external") public String callExternalService() {
	 * log.info("Inside callExternalService of UserController"); return
	 * userService.callExternalService(); }
	 */
}
