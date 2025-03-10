package com.userservice.userservice.service;




import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.userservice.client.DepartmentClient;
import com.userservice.userservice.entity.User;
import com.userservice.userservice.repository.UserRepository;
import com.userservice.userservice.vo.Department;
import com.userservice.userservice.vo.ResponseTemplateVO;

@Service
@Slf4j
public class UserService {

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

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

       // Department department =
           //     restTemplate.getForObject("http://department-service/departments/" + user.getDepartmentId()
                   //     ,Department.class);
        // Use Feign client to get department information
        Department department = departmentClient.getDepartmentById(user.getDepartmentId());
        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
