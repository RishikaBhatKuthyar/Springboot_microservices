package com.java.springboot.department_service.mapper;

import com.java.springboot.department_service.dto.DepartmentDto;
import com.java.springboot.department_service.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface AutoDepartmentMapper {
    //  MAPPER is like a key that lets you access the two methods
    AutoDepartmentMapper MAPPER= Mappers.getMapper(AutoDepartmentMapper.class);
    DepartmentDto mapToDepartmentDto(Department department);
    Department mapToDepartment(DepartmentDto departmentDto);
}
