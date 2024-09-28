package com.java.springboot.employee_service.mapper;

import com.java.springboot.employee_service.dto.EmployeeDto;
import com.java.springboot.employee_service.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface AutoEmployeeMapper {
     AutoEmployeeMapper MAPPER= Mappers.getMapper(AutoEmployeeMapper.class);
     EmployeeDto mapToEmployeeDto(Employee employee);
     Employee mapToEmployee(EmployeeDto employeeDto);
}
