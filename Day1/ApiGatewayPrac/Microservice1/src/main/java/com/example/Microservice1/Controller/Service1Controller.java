package com.example.Microservice1.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/service1")
public class Service1Controller {
	@GetMapping("/hello")
	public String hello() {
		return "Hello from Service 1";
	}
	
}
