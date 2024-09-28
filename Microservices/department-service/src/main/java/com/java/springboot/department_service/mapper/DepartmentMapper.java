package com.java.springboot.department_service.mapper;

import com.java.springboot.department_service.dto.DepartmentDto;
import com.java.springboot.department_service.entity.Department;

public class DepartmentMapper {
    //mapToDepartmentDto: Converts a Department entity to a DepartmentDto for sending data back to the client.

    public static DepartmentDto mapToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
//  MapToDepartment: Converts a DepartmentDto (client data) to a Department entity for saving into the database.
    public static Department MapToDepartment(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }
}

