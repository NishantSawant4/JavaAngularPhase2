package com.userservice.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.userservice.vo.Department;

@FeignClient(name = "department-service", url = "http://localhost:9001") // You can use the service name or URL
	public interface DepartmentClient {

	    @GetMapping("/departments/{departmentId}")
	    Department getDepartmentById(@PathVariable("departmentId") Long departmentId);
	    
	    
	    
	}

