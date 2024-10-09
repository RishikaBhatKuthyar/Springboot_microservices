package com.java.springboot.employee_service.service;

import com.java.springboot.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(url = "http://localhost:8080",value = "Dept")
// Using Eureka for service discovery and load balancing.
// By specifying the service name "DEPARTMENT-SERVICE", Eureka will automatically resolve the actual instance URLs (e.g., http://localhost:8080, http://localhost:8082)
// and handle load balancing between multiple instances of the service.
@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/dept/showDept/{department_code}")
    DepartmentDto getDepartment(@PathVariable("department_code") String departmentCode);
}
