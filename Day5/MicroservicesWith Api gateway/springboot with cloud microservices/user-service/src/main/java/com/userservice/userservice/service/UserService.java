package com.userservice.userservice.service;

import com.userservice.userservice.client.DepartmentClient;
import com.userservice.userservice.entity.User;
import com.userservice.userservice.repository.UserRepository;
import com.userservice.userservice.vo.Department;
import com.userservice.userservice.vo.ResponseTemplateVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    private static final String DEPARTMENT_SERVICE = "departmentService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentClient departmentClient;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "departmentServiceFallback")
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = departmentClient.getDepartmentById(user.getDepartmentId());
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    // Fallback method for Circuit Breaker
    public ResponseTemplateVO departmentServiceFallback(Long userId, Exception e) {
        log.error("Department Service is down, returning fallback response.", e);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        // Initialize Department directly with constructor
        Department fallbackDepartment = new Department(0L, "No Department", "No Address", "No Code");

        vo.setUser(user);
        vo.setDepartment(fallbackDepartment);

        return vo;
    }

}
