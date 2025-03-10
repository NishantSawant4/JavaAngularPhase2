package com.departmentservice.departmentservice.controller;

import com.departmentservice.departmentservice.entity.Department;
import com.departmentservice.departmentservice.service.DepartmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    private static final String DEPARTMENT_SERVICE = "departmentService";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

    @GetMapping("/external")
    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "externalServiceFallback")
    public String callExternalService() {
        log.info("Calling External Service");
        return restTemplate.getForObject("http://localhost:8082/api", String.class);
    }

    public String externalServiceFallback(Exception e) {
        log.error("External Service failed, falling back", e);
        return "Fallback response: External Service is currently unavailable.";
    }
}
