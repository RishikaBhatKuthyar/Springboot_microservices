package com.java.springboot.employee_service.service;

import com.java.springboot.employee_service.dto.ApiResponseDto;
import com.java.springboot.employee_service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee( Long EmployeeId);
}
