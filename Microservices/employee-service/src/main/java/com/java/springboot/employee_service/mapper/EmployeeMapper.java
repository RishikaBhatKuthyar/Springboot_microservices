package com.java.springboot.employee_service.mapper;

import com.java.springboot.employee_service.dto.ApiResponseDto;
import com.java.springboot.employee_service.dto.DepartmentDto;
import com.java.springboot.employee_service.dto.EmployeeDto;
import com.java.springboot.employee_service.entity.Employee;

public class EmployeeMapper {
//    database to client
    public static EmployeeDto MapToEmployeeDto(Employee employee ) {
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
            return employeeDto;
    }
//    client data to database
    public static Employee MapToEmployee(EmployeeDto employeeDto){
        Employee employee=new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail(),
            employeeDto.getDepartmentCode(),
            employeeDto.getOrganizationCode()
        );
        return employee;
    }
}
